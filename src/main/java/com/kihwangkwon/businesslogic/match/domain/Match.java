package com.kihwangkwon.businesslogic.match.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;
import org.springframework.boot.context.properties.ConstructorBinding;

import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="match_info")
@Getter
@Setter
@NoArgsConstructor
public class Match {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	private String matchId;
	private String gameVersion;
	private double gameLength;
	private Timestamp gameDatetime;
	
	private String tftSetNumber;
	private String dataVersion;
	private String queueId;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="matchInfoId", referencedColumnName = "id")	
	List<MatchPlayer> matchPlayerList;

	@Builder
	public Match(String matchId,String gameVersion, double gameLength,Timestamp gameDatetime,String tftSetNumber,String dataVersion,String queueId) {
		this.matchId = matchId;
		this.gameVersion = gameVersion;
		this.gameLength = gameLength;
		this.gameDatetime = gameDatetime;
		this.tftSetNumber = tftSetNumber;
		this.dataVersion = dataVersion;
		this.queueId = queueId;
	}
	
	/*
	@OneToOne
	@JoinColumn(name = "player_match_match_id")
	private PlayerMatch playerMatch;
	*/
}
