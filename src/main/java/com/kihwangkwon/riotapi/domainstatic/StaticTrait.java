package com.kihwangkwon.riotapi.domainstatic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StaticTrait {
	@Id
	@GeneratedValue
	private Long id;
	
	private String key;
	//시너지 명
	private String name;

	private String description;
	
	// 직업,계열 구분
	private String type;
	
	private String imageFileName;
	
	//각 시너지 등급별 필요 최소 기물 수
	private int bronze;
	private int silver;
	private int gold;
	private int chromatic;
}
