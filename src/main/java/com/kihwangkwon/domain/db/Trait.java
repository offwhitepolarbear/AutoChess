package com.kihwangkwon.domain.db;

import lombok.Getter;
import lombok.Setter;

/*
 * 버전별 특성 테이블
 * 버전을 Season 하고 서브버전하고 나눠야 될듯
 * 
 * */
@Getter
@Setter
public class Trait {
	private Trait traitName;
	private Trait traitNameKorean;
	private String traitEffect;
	private String traitEffectKorean;
	//private TraitTier traitTier;
	
	//특성 시즌 숫자(발동갯수)/티어 브실골플 /직업/성능등
}
