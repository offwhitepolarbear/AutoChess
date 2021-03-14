package com.kihwangkwon.riotapi.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kihwangkwon.properties.ConstructorProperties;
import com.kihwangkwon.riotapi.domain.RegionContinent;

@EnableConfigurationProperties(value = { ConstructorProperties.class })
public class RiotApiRequester {
	// 소환사 기본정보
	// 소환사 아이디로 경기 아이디 가져오는거
	// 가져온 경기 아이디 들 어떻게 처리할건지 ㅈ처리해야되고
	private ConstructorProperties constructorProperties;
	
	@Autowired
	RiotApiRequester(ConstructorProperties constructorProperties){
		this.constructorProperties = constructorProperties;
	}
	

	final String riotUrl = "api.riotgames.com/tft/";

	public String getSummonerById(String pid) {
		return null;
	}

	public String getMatch(String matchId) {
		return null;
	}

	public String getLeagueByTier(String tier) {
		return null;
	}

	public String getLeagueByLeagueId(String leagueId) {
		return null;
	}
	// json simple 로 파싱

	private String urlMaker(String region, String subUrl, String keyword){
		StringBuffer url = new StringBuffer();
		url.append(region);
		url.append(".");
		url.append(riotUrl);
		url.append(subUrl);
		url.append(keyword);
		url.append("?api_key="+constructorProperties.getApiKey());
		return url.toString();
	}
	
	//    이름=>puuid채굴 => 참가 매치 아이디 채굴 /tft/match/v1/matches/by-puuid/{puuid}/ids
	
	///
	/*
	The AMERICAS routing value serves NA, BR, LAN, LAS, and OCE. The ASIA routing value serves KR and JP. The EUROPE routing value serves EUNE, EUW, TR, and RU.
	*/
}