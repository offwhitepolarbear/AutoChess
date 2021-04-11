package com.kihwangkwon.riotapi.request;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerChampion;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerTrait;
import com.kihwangkwon.businesslogic.match.service.MatchRepository;
import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.ApiMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;
import com.kihwangkwon.riotapi.domainconvert.JsonToMatchDomain;
import com.kihwangkwon.riotapi.domainconvert.JsonToPlayerDomain;

@Service
public class GetObjectFromApi {
	
	private RiotApiRequester riotApiRequester;
	private JsonToMatchDomain jsonToMatchDomain;
	private JsonToPlayerDomain jsonToPlayerDomain;
	
	@Autowired
	public GetObjectFromApi(RiotApiRequester riotApiRequester
							, JsonToMatchDomain jsonToMatchDomain
							, JsonToPlayerDomain jsonToPlayerDomain) {
		this.riotApiRequester = riotApiRequester;
		this.jsonToMatchDomain = jsonToMatchDomain;
		this.jsonToPlayerDomain = jsonToPlayerDomain;
	}
	
	public Player getPlayer(RegionNation nation, String name) {
		String apiResponse = riotApiRequester.getSummonerByName(nation, name);
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
	
	public Match getMatch(RegionNation nation, String matchId) throws ParseException {
		String apiResponse  = riotApiRequester.getMatch(nation, matchId);
		
		Map map = jsonStringToMap(apiResponse);
		
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
