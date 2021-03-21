package com.kihwangkwon.businesslogic.match.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="match_info")
public class Match {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String matchId;
	private String gameVersion;
	private double gameLength;
	private Timestamp gameDatetime;
	
	private String tftSetNumber;
	private String dataVersion;
	private String queueId;

}
