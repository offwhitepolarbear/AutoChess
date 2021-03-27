package com.kihwangkwon.businesslogic.match.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MatchPlayerChampion {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	private Long matchPlayerId;
	
	private String matchId;
	private String puuid;
	
	private String championId;
	private String name;
	//가격 1~5
	private int cost;
	//챔피언 레벨 1~3
	private int star;
	
	private String chosen;
	
	private int itemOne;
	private int itemTwo;
	private int itemThree;

	@Builder
	public MatchPlayerChampion(String matchId, String puuid, String championId, int cost, int star, String chosen, int itemOne
			,int itemTwo, int itemThree, String name) {
		this.matchId=matchId;
		this.puuid=puuid; 
		this.championId = championId; 
		this.cost = cost;
		this.star = star; 
		this.chosen = chosen; 
		this.itemOne = itemOne;
		this.itemTwo = itemTwo;
		this.itemThree = itemThree;
		this.name = name;
	}	
}
