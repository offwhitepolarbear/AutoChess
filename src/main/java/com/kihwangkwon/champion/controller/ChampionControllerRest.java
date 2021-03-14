package com.kihwangkwon.champion.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.common.GetDataFromExternalApi;
import com.kihwangkwon.domain.db.Champion;
import com.kihwangkwon.properties.ClassPathProperties;
import com.kihwangkwon.properties.ConstructorProperties;

@EnableConfigurationProperties(value= {ConstructorProperties.class})
@RestController
@RequestMapping("/champion")
public class ChampionControllerRest {
	
	String testurl= "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/%EB%82%A8%EA%B7%B9%EA%B3%B0";
	String getTest = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/남극곰?api_key=";
	
	@Autowired
	ConstructorProperties constructorProperties;
	
	@Autowired
	GetDataFromExternalApi getDataFromExternalApi;
	
	@RequestMapping("/champions/{championName}")
	public List<Champion> championList(@PathVariable("championName") String championName) {
		System.out.println(constructorProperties.getApiKey());
		System.out.println("/champion/champions");
		System.out.println(championName);
		
		System.out.println (getDataFromExternalApi.postRequest(testurl));
		System.out.println(getDataFromExternalApi.getDataFromApi(getTest));
		
		List championList = new ArrayList<>();
		Champion garen = new Champion();
		garen.setNameKorean("가렌");
		Champion galio = new Champion();
		galio.setNameKorean("갈리오");
		Champion ganplank = new Champion();
		ganplank.setNameKorean("갱플랭크");
		Champion yone = new Champion();
		yone.setNameKorean("요네");
		
		championList.add(garen);
		championList.add(galio);
		championList.add(ganplank);
		championList.add(yone);
		
		return championList;
	}
}
