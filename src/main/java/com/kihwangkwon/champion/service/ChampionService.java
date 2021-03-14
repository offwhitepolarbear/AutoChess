package com.kihwangkwon.champion.service;

import java.util.List;

import com.kihwangkwon.domain.db.Champion;

public interface ChampionService {
	
	public int addChampion(Champion champion);
	
	public List getChampionList();
	
	
}
