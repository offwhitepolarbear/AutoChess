package com.kihwangkwon.staticdata.champion.service;

import java.util.List;

import com.kihwangkwon.staticdata.champion.domain.StaticChampion;

public interface StaticChampionService {
	public int insertStaticChampionAll();
	public int insertStaticChampion(StaticChampion staticChampion);
	public List staticChampionList();
}
