package com.kihwangkwon.businesslogic.player.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kihwangkwon.businesslogic.match.domain.Match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayerMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long playerId;
	
	private String region;
	private String puuid;
	private String matchId;
	
	public PlayerMatch(String region,String puuid, String matchId) {
		
	}
	
}
