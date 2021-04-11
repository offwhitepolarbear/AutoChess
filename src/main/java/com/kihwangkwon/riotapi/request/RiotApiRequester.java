package com.kihwangkwon.riotapi.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.common.GetDataFromExternalApi;
import com.kihwangkwon.properties.ConstructorProperties;
import com.kihwangkwon.riotapi.domain.RegionContinent;
import com.kihwangkwon.riotapi.domain.RegionNation;

@Service
@EnableConfigurationProperties(value = { ConstructorProperties.class })
public class RiotApiRequester {
	// 소환사 기본정보
	// 소환사 아이디로 경기 아이디 가져오는거
	// 가져온 경기 아이디 들 어떻게 처리할건지 ㅈ처리해야되고
	private ConstructorProperties constructorProperties;
	
	private ApiRequestURLMaker apiRequestURLMaker;
	
	@Autowired
	RiotApiRequester(ConstructorProperties constructorProperties, ApiRequestURLMaker apiRequestURLMaker){
		this.constructorProperties = constructorProperties;
		this.apiRequestURLMaker = apiRequestURLMaker;
	}

	public String getSummonerByName(RegionNation nation, String name) {
		String requestURL = apiRequestURLMaker.summonerApi(nation, name);
		return GetDataFromExternalApi.getDataFromApi(requestURL);
	}
	
	public String getSummonerBypuuid(RegionNation nation, String puuid) {
		String requestURL = apiRequestURLMaker.summonerApiPuuid(nation, puuid);
		return GetDataFromExternalApi.getDataFromApi(requestURL);
	}
	
	public String getMatchList(RegionNation nation, String puuid) {
		String requestURL = apiRequestURLMaker.matchList(nation, puuid);
		return GetDataFromExternalApi.getDataFromApi(requestURL);
	}
	
	public String getMatch(RegionNation nation, String matchId) {
		String requestURL = apiRequestURLMaker.matchByMatchId(nation, matchId);
		return GetDataFromExternalApi.getDataFromApi(requestURL);
	}
	
	
	public String getLeagueByTier(String tier) {
		return null;
	}

	public String getLeagueByLeagueId(String leagueId) {
		return null;
	}
	// json simple 로 파싱

	
	//    이름=>puuid채굴 => 참가 매치 아이디 채굴 /tft/match/v1/matches/by-puuid/{puuid}/ids
	
	///
	/*
	The AMERICAS routing value serves NA, BR, LAN, LAS, and OCE. The ASIA routing value serves KR and JP. The EUROPE routing value serves EUNE, EUW, TR, and RU.
	*/
}