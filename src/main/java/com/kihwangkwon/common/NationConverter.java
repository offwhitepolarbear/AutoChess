package com.kihwangkwon.common;

import com.kihwangkwon.riotapi.domain.RegionContinent;
import com.kihwangkwon.riotapi.domain.RegionNation;

public class NationConverter {
	
	//The AMERICAS routing value serves NA, BR, LAN, LAS, and OCE. 
	//The ASIA routing value serves KR and JP. 
	//The EUROPE routing value serves EUNE, EUW, TR, and RU.
	
	public RegionContinent getContinentByNation(RegionNation nation) {

		RegionContinent result = null;
		
		if(nation==RegionNation.br1) {
			result=RegionContinent.americas;
		}
		if(nation==RegionNation.la1) {
			result=RegionContinent.americas;
		}
		if(nation==RegionNation.la2) {
			result=RegionContinent.americas;
		}
		if(nation==RegionNation.na1) {
			result=RegionContinent.americas;
		}
		if(nation==RegionNation.oc1) {
			result=RegionContinent.americas;
		}

		if(nation==RegionNation.jp1) {
			result=RegionContinent.asia;
		}
		if(nation==RegionNation.kr) {
			result=RegionContinent.asia;
		}
		
		if(nation==RegionNation.eun1) {
			result=RegionContinent.europe;
		}
		if(nation==RegionNation.euw1) {
			result=RegionContinent.europe;
		}
		if(nation==RegionNation.tr1) {
			result=RegionContinent.europe;
		}
		if(nation==RegionNation.ru) {
			result=RegionContinent.europe;
		}

		return result;
	}
}
