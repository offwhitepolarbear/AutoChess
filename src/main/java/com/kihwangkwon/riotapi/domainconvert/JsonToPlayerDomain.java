package com.kihwangkwon.riotapi.domainconvert;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;

@Service
public class JsonToPlayerDomain {
	private ConvertCommonUtil convertCommonUtil;
	
	@Autowired
	public JsonToPlayerDomain(ConvertCommonUtil convertCommonUtil){
		this.convertCommonUtil = convertCommonUtil;
	}
	
	public Player getPlayer(Map map, RegionNation region) {
		
		String playerId = (String) map.get("id");
		String accountId = (String) map.get("accountId");
		String puuid = (String) map.get("puuid");
		String name = (String) map.get("name");
		String profileIconId = convertCommonUtil.objectNumberToString(map.get("profileIconId")) ;
		Timestamp revisionDate = convertCommonUtil.obejectToTimestamp(map.get("revisionDate")); 
		int summonerLevel = convertCommonUtil.objectToInt(map.get("summonerLevel"));
		
		Player player = Player.builder()
				.playerId(playerId)
				.accountId(accountId)
				.puuid(puuid)
				.name(name)
				.profileIconId(profileIconId)
				.revisionDate(revisionDate)
				.summonerLevel(summonerLevel)
				.region(region.toString())
				.build();

		return player;
	}
	
	public List<PlayerMatch> getPlayerMatchList(List<Object> matchIdList, List<PlayerMatch> matchList, RegionNation region, String puuid){
		List<PlayerMatch> playerMatchList = new ArrayList<PlayerMatch>();
		List<String> newMatchIdList = getNewMatchIdList(matchIdList,matchList);
		for(String matchId : newMatchIdList) {
			PlayerMatch playerMatch = PlayerMatch.builder()
										.region(region.toString())
										.puuid(puuid)
										.matchId(matchId)
										.build();
			Match match = new Match();
			match.setMatchId(matchId);
			//playerMatch.setMatch(match);
			playerMatchList.add(playerMatch);
		}
		
		return playerMatchList;
	}
	
	private List<String> getNewMatchIdList(List<Object> matchIdList, List<PlayerMatch> matchList){
		List <String> newMatchIdList = new ArrayList<String>();
		
		if(matchIdList != null) {
			for(Object matchIdFromApi : matchIdList) {
				String matchId = (String)matchIdFromApi;
				newMatchIdList.add(matchId);
			}
		}
		
		List<String> latestDBMatchList = getLastMatchIdList(matchList);
		
		if(newMatchIdList.size()>0) {
			newMatchIdList = removeDuplicatedMatchId(newMatchIdList, latestDBMatchList);
		}
		
		return newMatchIdList;
	}

	private List<String> getLastMatchIdList(List<PlayerMatch> matchList) {
		List<String> matchIdList = new ArrayList<String>();
		if(matchList != null) {
			for(PlayerMatch match:matchList) {
				String matchId = match.getMatchId();
				matchIdList.add(matchId);
			}
		}
		return matchIdList;
	}
	
	private List<String> removeDuplicatedMatchId(List<String> apiMatchList, List<String> latestDBMatchList) {
		// api에서는 전체 전적을 가져오고 db에 있는 전적은 일부기 때문에 
		// 전체 전적에서 db에 있는 일부를 제외한 신규 아이디만 보내주면 됨
		if(latestDBMatchList != null) {
			for(String matchId:latestDBMatchList) {
				apiMatchList.remove(matchId);
			}
		}
		return apiMatchList;
	}
	
	
	
}
