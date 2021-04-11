package com.kihwangkwon.businesslogic.player.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;
import com.kihwangkwon.riotapi.request.GetObjectFromApi;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	private GetObjectFromApi getObjectFromApi;
	private PlayerRepository playerRepository;
	private PlayerMatchRepository playerMatchRepository;
	
	@Autowired
	public PlayerServiceImpl(GetObjectFromApi getObjectFromApi
							, PlayerRepository playerRepository
							, PlayerMatchRepository playerMatchRepository) {
		
		this.getObjectFromApi = getObjectFromApi;
		this.playerRepository = playerRepository;
		this.playerMatchRepository = playerMatchRepository;
	}
	
	@Override
	public Player getPlayerByName(RegionNation region, String playerName) {
		
		Player player = playerRepository.findByRegionAndName(region.toString(), playerName);
		
		if(player != null) {
			//최종조회시간 확인해서 어느정도 차이나면 전적 업데이트 필요
			updatePlayerMatchList(RegionNation.valueOf(player.getRegion()), player.getPuuid());
		}
		
		if(player == null) {
			player = getPlayerFromApi(region,playerName);
			
			if(player != null) {
				updatePlayerMatchList(RegionNation.valueOf(player.getRegion()), player.getPuuid());
			}
		
		}
		
		return player;
	}
	
	private Player getPlayerFromApi(RegionNation region, String playerName) {
		Player player = getObjectFromApi.getPlayer(region, playerName);
		//화면에서 검색어 입력 방식이라 없는 유저일 수 있음 널 예외처리 필요
		if(player != null) {
			player = playerRepository.save(player);
		}
		
		return player;
	}
	
	@Override
	public Player getPlayerByPuuid(RegionNation region, String puuid) {
		Player player = playerRepository.findByRegionAndPuuid(region.toString(), puuid);
		if(player == null) {
			player = getPlayerFromApiByPuuid(region, puuid);
			
			if(player != null) {
				updatePlayerMatchList(RegionNation.valueOf(player.getRegion()), player.getPuuid());
			}
		
		}
		return player;
	}
	


	private Player getPlayerFromApiByPuuid(RegionNation region, String puuid) {
		Player player = getObjectFromApi.getPlayerByPuuid(region, puuid);
		//이름검색과 달리 얘는 할당된 puuid로만 호출될 거라 null 처리 필요없음
		//id 값 가져갈 수 있게 save 실행 후 할당
		player = playerRepository.save(player);
		return player;
	}
	
	
	@Override
	public List<PlayerMatch> updatePlayerMatchList(RegionNation region, String puuid) {
		
		List<PlayerMatch> matchList = playerMatchRepository.findByPuuid(puuid);
		
		List<PlayerMatch> latestMatchList = getObjectFromApi.getMatchList(region, puuid, matchList);
		//업데이트 할 항목이 있을 경우 업데이트 후 재조회 해서 재조회한 목록을  리턴
		if(latestMatchList.size()>0) {
			for(PlayerMatch playerMatch : latestMatchList) {
				playerMatchRepository.save(playerMatch);
			}
			matchList = playerMatchRepository.findByPuuid(puuid);
		}
		
		return matchList;
	}

	
	//전적검색에서 매치 유저 이름이 안나올경우 새로고침 하기 위해 만듬
	@Override
	public List<Player> getPlayerListByPuuid(List<Player> playerList) {
		List<Player> playerListFromApi = new ArrayList<Player>();
		for(Player player:playerList) {
			RegionNation region = RegionNation.valueOf(player.getRegion());
			String puuid = player.getPuuid();
			Player apiPlayer = getObjectFromApi.getPlayerByPuuid(region, puuid);
			playerList.add(apiPlayer); 
		}
		return playerListFromApi;
	}

	@Override
	public List<PlayerMatch> getPlayerMatchListByMatchId(String matchId) {
		return playerMatchRepository.findByMatchId(matchId);
	}

	@Override
	public List<PlayerMatch> updatePlayerMatchFromMatchApi(Match match,RegionNation region) {
		String matchId = match.getMatchId();
		List<PlayerMatch> playerMatchList = getPlayerMatchListByMatchId(matchId);
		List<MatchPlayer> matchPlayerList = match.getMatchPlayerList();
		List<PlayerMatch> playerMatchListFromMatch = new ArrayList<PlayerMatch>();
		for(MatchPlayer matchPlayer : matchPlayerList) {
			String puuid = matchPlayer.getPuuid();
			boolean isDuplicated = checkPlayerMatchDuplication(playerMatchList, puuid);
			if(!isDuplicated) {
				
				PlayerMatch playerMatch = PlayerMatch.builder()
											.puuid(puuid)
											.matchId(matchId)
											.region(region.toString())
											.build();
				playerMatchListFromMatch.add(playerMatch);
			}
		}
		
		if(playerMatchListFromMatch!=null) {
			for(PlayerMatch playerMatch:playerMatchListFromMatch) {
				playerMatchRepository.save(playerMatch);
			}
		}
		
		return playerMatchListFromMatch;
	}
	
	private boolean checkPlayerMatchDuplication(List<PlayerMatch> dbPlayerMatchList, String puuid){
		boolean result = false;
		//db에 해당하는 매치리스트가 있을 때 api에서 온거에서 db에 있는 애들을 제거
		if(dbPlayerMatchList!=null) {
			for(PlayerMatch playerMatch : dbPlayerMatchList) {
				String dbPuuid = playerMatch.getPuuid();
				if(dbPuuid.equals(puuid)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}


}
