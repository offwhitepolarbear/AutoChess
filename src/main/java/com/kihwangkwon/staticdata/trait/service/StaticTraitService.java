package com.kihwangkwon.staticdata.trait.service;

import java.util.List;

import com.kihwangkwon.staticdata.trait.domain.StaticTrait;

public interface StaticTraitService {
	
	public int insertStaticTraitAll();
	public int insertStaticTrait(StaticTrait staticTrait);
	public List staticTraitList();
}
