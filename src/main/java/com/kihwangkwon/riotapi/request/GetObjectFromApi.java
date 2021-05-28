package com.kihwangkwon.riotapi.request;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.hibernate.property.access.spi.GetterFieldImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;
import com.kihwangkwon.riotapi.domainconvert.JsonToMatchDomain;
import com.kihwangkwon.riotapi.domainconvert.JsonToPlayerDomain;

@Service
public class GetObjectFromApi {
	
	private RiotApiRequester riotApiRequester;
	private JsonToMatchDomain jsonToMatchDomain;
	private JsonToPlayerDomain jsonToPlayerDomain;
	private Gson gson;
	
	@Autowired
	public GetObjectFromApi(RiotApiRequester riotApiRequester
							, JsonToMatchDomain jsonToMatchDomain
							, JsonToPlayerDomain jsonToPlayerDomain
							, Gson gson) {
		this.riotApiRequester = riotApiRequester;
		this.jsonToMatchDomain = jsonToMatchDomain;
		this.jsonToPlayerDomain = jsonToPlayerDomain;
		this.gson = gson;
	}
	
	//DB에 존재하지 않는 플레이어에 대해 검색하는 경우 이름값이 유효한지 등의 검사 필요하기 때문에 중간처리 추가된 메서드 호출
	public Player getPlayerFromApiFirst(RegionNation nation, String name){
		Player result = null;
		String apiResponse = riotApiRequester.getSummonerByName(nation, name);
		
		// api 에서 정상 리턴 온경우(검색이 된 경우)
		if (apiResponse!=null) {
			result = getPlayer(nation, name);
		}
		//응답없는 경우 (존재하지 않는 아이디 일 경우)
		else {
			//아무 처리 하지 않음으로써 null player를 리턴
		}
		return result;
	}
	
	public Player getPlayer(Player player, RegionNation nation, String name){
		//db에 플레이어 있는경우 id 할당하고 api에서 가져옴
		if(player!=null) {
			Long id = player.getId();
			player = getPlayer(nation, name);
			player.setId(id);
		}
		//db에 플레이어 없는경우
		else {
			player = getPlayer(nation, name);
		}
		return player;
	}
	
	public Player getPlayer(RegionNation nation, String name){
		String apiResponse = riotApiRequester.getSummonerByName(nation, name);
		Player player = null;
		//정상적으로 응답이 온 경우 파싱해서 플레이어 객체 생성 그외의 경우에는 null player 리턴됨
		if(apiResponse != null) {
			Map map = null;
			try {
				map = jsonStringToMap(apiResponse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			player = jsonToPlayerDomain.getPlayer(map, nation);
			String apiResponsePlayerDetail = riotApiRequester.getSummonerDetailBySummonerId(nation, player.getSummonerId());
			apiResponsePlayerDetail = playerDetailRankToInt(apiResponsePlayerDetail);
			player = jsonToPlayerDomain.getMergedPlayer(player, apiResponsePlayerDetail);
		}
		return player;
	}
	
	
	
	public Player getPlayerByNationAndName(RegionNation nation, String name){
		String apiResponse = riotApiRequester.getSummonerByName(nation, name);
		Player player = null;
		//정상적으로 응답이 온 경우 파싱해서 플레이어 객체 생성 그외의 경우에는 null player 리턴됨
		if(apiResponse != null) {
			Map map = null;
			try {
				map = jsonStringToMap(apiResponse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			player = jsonToPlayerDomain.getPlayer(map, nation);
			String apiResponsePlayerDetail = riotApiRequester.getSummonerDetailBySummonerId(nation, player.getSummonerId());
			apiResponsePlayerDetail = playerDetailRankToInt(apiResponsePlayerDetail);
			player = jsonToPlayerDomain.getMergedPlayer(player, apiResponsePlayerDetail);
		}
		return player;
	}
	
	//player 상세 리스트 형식으로 오는거 벗겨내고 랭크 값 숫자로 대입시키는 메서드
	private String playerDetailRankToInt(String apiResponsePlayerDetail) {
		Gson gson = new Gson();
		Map[] playerDetailArray = gson.fromJson(apiResponsePlayerDetail, Map[].class);
		Map playerMap = null;
		String rankQueue =  "RANKED_TFT";
		
		//String rankTurbo = "RANKED_TFT_TURBO";
		
		for(Map playerDetailMap : playerDetailArray) {
			String queueType = (String) playerDetailMap.get("queueType");
			
			if(queueType.equals(rankQueue)) {
				playerMap = playerDetailMap;
			}
				
		}
		
		String rank = (String)playerMap.get("rank");
		
		if(rank != null) {
			int rankValue = getRank(rank);
			playerMap.put("rank", rankValue);
		}
		
		return gson.toJson(playerMap);
	}
	
	private int getRank(String rank) {
		int result = 0;
		
		if(rank.equals("I")) {
			result = 1;
		}
		if(rank.equals("II")) {
			result = 2;
		}
		if(rank.equals("III")) {
			result = 3;
		}
		if(rank.equals("IV")) {
			result = 4;
		}
		if(rank.equals("V")) {
			result = 5;
		}		
		return result;
	}
	
	public Player getPlayerByPuuid(RegionNation nation, String puuid) {
		String apiResponse = riotApiRequester.getSummonerBypuuid(nation, puuid);
		Map map = null;
		Player player = null;
		try {
			map = jsonStringToMap(apiResponse);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		if(map!=null) {
			player = jsonToPlayerDomain.getPlayer(map,nation);
		}
		
		return player;
	}
	
	public Player getPlayerDetailByPlayerId(Player player) {
		RegionNation region = RegionNation.valueOf(player.getRegion());
		String summonerId = player.getSummonerId();
		String apiResponse = riotApiRequester.getSummonerDetailBySummonerId(region, summonerId);
		
		return player;
	}
	
	public List<PlayerMatch> getMatchList(RegionNation region, String puuid, List<PlayerMatch> matchList){
		String apiResponse  = riotApiRequester.getMatchList(region, puuid);
		
		List<Object> matchIdList = null;
		try {
			matchIdList = jsontStringToList(apiResponse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<PlayerMatch> playerMatchList = jsonToPlayerDomain.getPlayerMatchList(matchIdList, matchList, region, puuid);
		
		return playerMatchList;
	}
	
	public Match getMatch(RegionNation nation, String matchId) {
		String apiResponse  = riotApiRequester.getMatch(nation, matchId);
		
		Map map = null;
		try {
			map = jsonStringToMap(apiResponse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Match match = jsonToMatchDomain.getMatchObject(map);
		
		return match;
	}
	
	private Map jsonStringToMap(String json) throws ParseException {
		Map map = null;
		if(json!=null) {
			JSONParser jsonParser = new JSONParser(json);
			map =  jsonParser.parseObject();
		}
		return map;
	}
	
	private List<Object> jsontStringToList(String json) throws ParseException{
		JSONParser jsonParser = new JSONParser(json);
		List<Object> objectList = jsonParser.parseArray();
		return objectList;
	}
}
