package com.kihwangkwon.riotapi.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

public class ApiMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
    "queue_id": 1100,
    "tft_set_number": 4
	 */
	//metadata하위
	private String dataVersion;
	private String matchId;
	
	//info 하위
	//게임 시작시간? timestamp형식
	private Timestamp gameDateTime;
	//게임길이 초
	private double gameLength;
	
	//게임버전
	private String gameVersion;
	private int queId;
	private String tftSetNumber;
	
		 //  "data_version": "5",
	       // "match_id": "KR_4893814088",
	        //참가자 매치아이디 빼가서 따로 테이블 만들건데 여기서 들고 있을 필요가 있는지 판단 필요
	        //"participants":
	//info 하위
//		"game_datetime": 1609663335084,
//        "game_length": 2176.957275390625,
//        "game_version": "Version 10.25.350.1724 (Dec 15 2020/18:30:02) [PUBLIC] ",
//        "queue_id": 1100,
//        "tft_set_number": 4
      
}
