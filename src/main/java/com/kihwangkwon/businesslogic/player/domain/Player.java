package com.kihwangkwon.businesslogic.player.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String accountId;
	private String puuid;
	private String name;
	private RegionNation region;
	private String profileIconId;
	private String revisionDate;
	private int summonerLevel;
	private String server;
	private Tier tier;
	private String tierLevel;
	private String tierPoint;
	private Timestamp lastRequest;
	
	@OneToMany
	@JoinColumn(name = "playerId", referencedColumnName="id") 
	private List<PlayerMatch> playerMatchList;
	
	@Builder
	public Player(String accountId, String puuid, RegionNation region) {
		
	}
	

}