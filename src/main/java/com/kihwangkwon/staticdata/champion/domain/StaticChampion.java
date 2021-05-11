package com.kihwangkwon.staticdata.champion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StaticChampion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 5)//DB에 적용되는 컬럼의 크기
	private String tftSetNumber;
	
	private String name;
	
	private String championId;
	
	private int cost;
	
	private String traitOne;
	private String traitTwo;
	private String traitThree;
	
}
