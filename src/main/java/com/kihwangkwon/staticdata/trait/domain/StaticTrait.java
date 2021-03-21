package com.kihwangkwon.staticdata.trait.domain;

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
public class StaticTrait {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String traitKey;
	//시너지 명

	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	// 직업,계열 구분
	private String traitType;
	private String imageFileName;
	
	//각 시너지 등급별 필요 최소 기물 수
	private int bronze;
	private int silver;
	private int gold;
	private int chromatic;
}
