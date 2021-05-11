package com.kihwangkwon.businesslogic.player.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		
		player = getObjectFromApi.getPlayer(player, region, playerName);

		//정상적으로 검색이되서 player값이 리턴된 경우에만 db에 저장 후 playerMatch 저장
		if(player!=null) {
			player = playerRepository.save(player);
			updatePlayerMatchList(region, player.getPuuid());
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
		player = playerRepository.save(player);
		return player;
	}
	
	@Override
	public List<PlayerMatch> updatePlayerMatchList(RegionNation region, String puuid) {
		
		List<PlayerMatch> matchList = playerMatchRepository.findByPuuid(puuid);
		
		List<PlayerMatch> latestMatchList = getObjectFromApi.getMatchList(region, puuid, matchList);
		//업데이트 할 항목이 있을 경우 업데이트 후 재조회 해서 재조회한 목록을  리턴
		List<PlayerMatch> updateList = checkPlayerMatchListDupliCated(matchList, latestMatchList);
		
		for(PlayerMatch playerMatch : updateList) {
			playerMatchRepository.save(playerMatch);
		}
		
		return matchList;
	}
	
	private List<PlayerMatch> checkPlayerMatchListDupliCated(List<PlayerMatch> dbList, List<PlayerMatch> apiList){
		for(PlayerMatch apiPlayerMatch:apiList) {
			for(PlayerMatch dbPlayerMatch: dbList) {
				String apiMatchId = apiPlayerMatch.getMatchId();
				String dbMatchId = dbPlayerMatch.getMatchId();
				if(apiMatchId.equals(dbMatchId)) {
					apiList.remove(apiPlayerMatch);
					dbList.remove(dbPlayerMatch);
				}
			}
		}
		
		return apiList;
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
	public List<PlayerMatch> updatePlayerMatchFromMatchApi(Match match, RegionNation region) {
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

	private List<PlayerMatch> savePlayerMatchList(List<PlayerMatch> playerMatchList){
		return null;
	}

	@Override
	public List<PlayerMatch> getPlayerMatchListByPuuid(RegionNation region, String puuid) {
		Sort sort = Sort.by(Direction.DESC,"matchId");
		return playerMatchRepository.findByRegionAndPuuid(region.toString(), puuid, sort);
	}
	
	
}
