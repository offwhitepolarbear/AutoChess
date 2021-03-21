package com.kihwangkwon.businesslogic.match.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MatchPlayerChampion {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	private String matchId;
	private String puuid;
	
	private String championId;
	private String name;
	
	private int cost;
	
	private int itemOne;
	private int itemTwo;
	private int itemThree;

	
	
/*
    "character_id": "TFT4_Vayne",
    "items": [],
    "name": "",
    "rarity": 0, 0~4 1코~5코
    "tier": 1 1~3 1성 2성 3성
	*/
}
