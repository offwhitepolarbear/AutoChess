package com.kihwangkwon.riotapi.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMatchPlayerChampion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//매치아이디
	private String matchId;
	
	//매치 유저
	private String puuid;
	
	//캐릭터 명 (두개 올리면 중복뜸 괜찮나)
	private String characterId;
	
	//아이템 넘버 세개까지
	private String itemOne;
	private String itemTwo;
	private String itemThree;
	
	//다 비어있는데 무슨 데이터인지? api에서 리턴 하니까 일단 놔둠
	private String name;
	
	// 선받자 있는 경우에만 뜸
	private String chosen;
	
	// 1~5코
	private String rarity;
	
	//1~3성
	private String tier; 

}
