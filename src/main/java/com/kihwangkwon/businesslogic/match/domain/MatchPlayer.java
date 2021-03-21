package com.kihwangkwon.businesslogic.match.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MatchPlayer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String matchId;
	private String puuid;
	
	private int goldLeft;
	private int lastRound;
	private int level;
	private int placement;
	
	private int damegeToPlayers;
	//킬수
	private int playersEliminated;
	//탈락 시간
	private double timeEliminated;
	
	private String contentId;
	private String skinId;
	private String species;
	/*
	 "companion": {
        "content_ID": "89222483-2823-410c-8a05-d2044e7c9a76",
        "skin_ID": 3,
        "species": "PetFairy"
    },
	*/
	
}
