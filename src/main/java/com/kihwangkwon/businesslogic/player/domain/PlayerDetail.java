package com.kihwangkwon.businesslogic.player.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
https://kr.api.riotgames.com/tft/league/v1/entries/by-summoner/summonerId
*/
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
public class PlayerDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String summonerName;
	private String leagueId;
	private String queueType;
	
	//티어
	private String tier;
	//티어 숫자
	private int rank;
	//리그 점수
	private int	leaguePoints;
	
	private int wins;
	private int losses;
	
	private boolean veteran;
	private boolean inactive;
	private boolean freshBlood;
	private boolean hotStreak;
	
	@Builder
	public PlayerDetail(String summonerName
			,String leagueId
			,String queueType
			,String tier
			,int rank
			,int leaguePoints
			,int wins
			,int losses
			,boolean veteran
			,boolean inactive
			,boolean freshBlood
			,boolean hotStreak) {
		this.summonerName = summonerName;
		this.leagueId = leagueId;
		this.queueType = queueType;
		this.tier = tier;
		this.rank = rank;
		this.leaguePoints = leaguePoints;
		this.wins = wins;
		this.losses = losses;
		this.veteran = veteran;
		this.inactive = inactive;
		this.freshBlood = freshBlood;
		this.hotStreak = hotStreak;
	}

}