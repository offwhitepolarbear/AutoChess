package com.kihwangkwon.businesslogic.player.domain;


import java.math.MathContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.kihwangkwon.businesslogic.match.domain.Match;

import lombok.Builder;
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
	
	private String region;
	private String puuid;
	private String matchId;

	@Builder
	public PlayerMatch(String region,String puuid, String matchId) {
		this.region = region;
		this.puuid = puuid;
		this.matchId = matchId;
	}
	
	/*
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(referencedColumnName = "matchId", foreignKey = @ForeignKey(name = "none"))
	private Match match;
	 */
	//joincolumn name = 가져올 테이블의 컬럼 referencedColumnName 현재 객체에서 조인에 사용할 값
	//foregin키 넌으로 할경우 제약조건 없이 컬럼이 하나 생김 
}
