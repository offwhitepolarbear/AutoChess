package com.kihwangkwon.domain;

import com.kihwangkwon.domain.enums.Tier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League {

	private Tier leagueTier;
	private String division;
	private String leagudId;
	
}
