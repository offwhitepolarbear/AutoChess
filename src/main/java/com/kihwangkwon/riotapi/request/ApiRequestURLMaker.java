package com.kihwangkwon.riotapi.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.kihwangkwon.common.NationConverter;
import com.kihwangkwon.domain.League;
import com.kihwangkwon.properties.ConstructorProperties;
import com.kihwangkwon.riotapi.domain.RegionNation;

@Service
@EnableConfigurationProperties(value = { ConstructorProperties.class })
public class ApiRequestURLMaker {
	
	private NationConverter nationConverter;
	private ConstructorProperties constructorProperties;
	
	@Autowired
	public ApiRequestURLMaker(NationConverter nationConverter, ConstructorProperties constructorProperties) {
		this.nationConverter = nationConverter;
		this.constructorProperties = constructorProperties;
	}
	
	public String leagueApi(String region, League league) {
		return null;
	}
	
	public String summonerApi(RegionNation nation, String summonerName) {
		StringBuffer url = new StringBuffer();
		url.append("https://");
		url.append(nation);
		url.append(".");
		url.append("api.riotgames.com/tft/summoner/v1/summoners/by-name/");
		url.append(summonerName);
		url.append("?");
		url.append("api_key=");
		url.append(constructorProperties.getApiKey());
		return url.toString();
	}
	
	public String summonerApiPuuid(RegionNation nation, String puuid) {
		StringBuffer url = new StringBuffer();
		url.append("https://");
		url.append(nation);
		url.append(".");
		url.append("api.riotgames.com/tft/summoner/v1/summoners/by-puuid/");
		url.append(puuid);
		url.append("?");
		url.append("api_key=");
		url.append(constructorProperties.getApiKey());
		return url.toString();
	}
	
	
	public String matchList(RegionNation nation, String puuid) {
		StringBuffer url = new StringBuffer();
		url.append("https://");
		url.append(nationConverter.getContinentByNation(nation));
		url.append(".api.riotgames.com/tft/match/v1/matches/by-puuid/");
		url.append(puuid);
		//count 만큼 전적 가져옴
		url.append("/ids?count=99999&");
		url.append("api_key=");
		url.append(constructorProperties.getApiKey());
		return url.toString();
	}
	
	public String matchByMatchId(RegionNation nation, String matchId) {
		StringBuffer url = new StringBuffer();
		url.append("https://");
		url.append(nationConverter.getContinentByNation(nation));
		url.append(".api.riotgames.com/tft/match/v1/matches/");
		url.append(matchId);
		url.append("?api_key=");
		url.append(constructorProperties.getApiKey());
		System.out.println(constructorProperties.getApiKey());
		return url.toString();
	}
	
}
