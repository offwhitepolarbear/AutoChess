package com.kihwangkwon.staticdata.champion.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.common.StaticJsonParsing;
import com.kihwangkwon.domain.db.Champion;
import com.kihwangkwon.staticdata.champion.domain.StaticChampion;

@Service
public class StaticChampionServiceImpl implements StaticChampionService{
	
	private StaticChampionRepository staticChampionRepository;
	private StaticJsonParsing staticJsonParsing;
	
	@Autowired
	public StaticChampionServiceImpl(StaticChampionRepository staticChampionRepository, StaticJsonParsing staticJsonParsing) {
		this.staticChampionRepository = staticChampionRepository;
		this.staticJsonParsing = staticJsonParsing;
	}
	
	@Override
	public int insertStaticChampionAll(String version) {
		List<HashMap> list = staticJsonParsing.championList(version);
		for(HashMap hashMap : list) {
			insertStaticChampion(hashMapToStaticChampion(hashMap, version));
		}
		return 0;
	}

	@Override
	public StaticChampion insertStaticChampion(StaticChampion staticChampion) {
		return staticChampionRepository.save(staticChampion);
	}

	@Override
	public List staticChampionList() {
		return staticChampionRepository.findAll();
	}
	
	private StaticChampion hashMapToStaticChampion(HashMap hashMap, String version) {
		StaticChampion champion = new StaticChampion();
		
		String tftSetNumber = staticJsonParsing.versionConvert(version);
		
		String name = (String) hashMap.get("name");
		String championId = (String) hashMap.get("championId");
		int cost = ((BigInteger)hashMap.get("cost")).intValue();
		
		champion.setTftSetNumber(tftSetNumber);
		
		champion.setChampionId(championId);
		champion.setCost(cost);
		champion.setName(name);
		
		champion = setTraitToStaticChampion(champion,hashMap);
		return champion;
	}
	
	private StaticChampion setTraitToStaticChampion(StaticChampion champion, HashMap hashMap) {
		List<String> traitList = (List<String>) hashMap.get("traits");
		int index = 0;
		for(String trait:traitList) {
			index++;
			if(index==1) {
				champion.setTraitOne(trait);
			}
			if(index==2) {
				champion.setTraitTwo(trait);	
			}
			if(index==3) {
				champion.setTraitThree(trait);
			}
		}
		
		return champion;
	}
	

}
