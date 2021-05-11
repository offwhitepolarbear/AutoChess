package com.kihwangkwon.staticdata.trait.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.common.StaticJsonParsing;
import com.kihwangkwon.staticdata.trait.domain.StaticTrait;

@Transactional
@Service
public class StaticTraitServiceImpl implements StaticTraitService {

	private StaticTraitRepository staticTraitRepository;
	private StaticJsonParsing staticJsonParsing;
	
	@Autowired
	StaticTraitServiceImpl(StaticTraitRepository staticTraitRepository, StaticJsonParsing staticJsonParsing){
		this.staticTraitRepository = staticTraitRepository;
		this.staticJsonParsing = staticJsonParsing;
	}

	@Override
	public int insertStaticTrait(StaticTrait staticTrait) {
		 staticTraitRepository.save(staticTrait);
		 return 1;
	}
	
	@Override
	public int insertStaticTraitAll(String version) {
		int insertCount = 0;
		List<Object> traitList = staticJsonParsing.traitList(version);
		for(Object trait : traitList) {
			insertStaticTrait(objectToStaticTrait(trait, version));
			insertCount++;
		}
		return insertCount;
	}
	
	@Override
	public List staticTraitList() {
		return staticTraitRepository.findAll();
	}
	
	public StaticTrait objectToStaticTrait(Object object, String version) {
		HashMap hashMap = (HashMap)object;
		
		String tftSetNumber = staticJsonParsing.versionConvert(version);
		
		String key = (String) hashMap.get("key");
		String name = (String) hashMap.get("name");
		String description = (String) hashMap.get("description");
		String type = (String) hashMap.get("type");
		List<HashMap> setList = (List<HashMap>) hashMap.get("sets");
		
		StaticTrait staticTrait = new StaticTrait();
		
		staticTrait.setTftSetNumber(tftSetNumber);
		
		staticTrait.setTraitKey(key);
		staticTrait.setName(name);
		staticTrait.setDescription(description);
		staticTrait.setTraitType(type);
		
		staticTrait = setSet(staticTrait,setList);
		
		return staticTrait;
	}
	
	public StaticTrait setSet(StaticTrait staticTrait, List<HashMap> setList) {
		for(HashMap hashMap : setList) {
			String style = (String) hashMap.get("style");
			int min = ((BigInteger) hashMap.get("min")).intValue();
			
			//해당 스타일 별로 할당
			if(style.equals("bronze")) {
				staticTrait.setBronze(min);
			}
			if(style.equals("silver")) {
				staticTrait.setSilver(min);
			}
			if(style.equals("gold")) {
				staticTrait.setGold(min);
			}

			if(style.equals("chromatic")) {
				staticTrait.setChromatic(min);
			}
			
		}
		
		return staticTrait;
	}


}
