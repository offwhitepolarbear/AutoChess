package com.kihwangkwon.businesslogic.player.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;

import com.kihwangkwon.domain.enums.Tier;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Player implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId(mutable = false)
	private String summonerId;
	
	@NaturalId(mutable = false)
	private String puuid;
	
	private String accountId;
	private String name;
	private String region;
	private String profileIconId;
	private Timestamp revisionDate;
	private int summonerLevel;
	private Timestamp lastRequest;
	
	/*아래는 player_id로 request 따로 보내야 가져올 수 있는 정보*/
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
	/*
	@OneToMany
	@Cascade(CascadeType.PERSIST)
	@JoinColumn(name = "puuid", referencedColumnName="puuid") 
	private List<PlayerMatch> playerMatchList;
	*/
	@Builder
	public Player(String summonerId
			, String accountId
			, String puuid
			, String name
			, String profileIconId
			, Timestamp revisionDate 
			, int summonerLevel
			, String region
			/*아래는 playerId로 request 따로 보내서 가져와야 하는 값*/
			, String leagueId
			, String queueType
			, String tier
			, int rank
			, int leaguePoints
			, int wins
			, int losses
			, boolean veteran
			, boolean inactive
			, boolean freshBlood
			, boolean hotStreak
			) {
		this.summonerId = summonerId;
		this.accountId = accountId;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
		this.region = region;
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