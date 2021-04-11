package com.kihwangkwon.businesslogic.player.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.kihwangkwon.domain.enums.Tier;
import com.kihwangkwon.riotapi.domain.RegionNation;

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
	
	private String playerId;
	
	@NaturalId(mutable = false)
	private String puuid;
	
	private String accountId;
	private String name;
	private String region;
	private String profileIconId;
	private Timestamp revisionDate;
	private int summonerLevel;
	private String server;
	private Tier tier;
	private String tierLevel;
	private String tierPoint;
	private Timestamp lastRequest;
	
	@OneToMany
	@JoinColumn(name = "puuid", referencedColumnName="puuid") 
	private List<PlayerMatch> playerMatchList;
	
	@Builder
	public Player(String playerId
			, String accountId
			, String puuid
			, String name
			, String profileIconId
			, Timestamp revisionDate 
			, int summonerLevel
			, String region) {
		this.playerId = playerId;
		this.accountId = accountId;
		this.puuid = puuid;
		this.name = name;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
		this.region = region;
	}
	

}