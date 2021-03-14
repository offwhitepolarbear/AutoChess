package com.kihwangkwon.domain;

import lombok.Getter;
import lombok.Setter;


/*
 * 테이블 용 아님 테이블은 덱 테이블에서 퍼오면 되고 
 * */
@Getter
@Setter
public class ChampionItem {
	private Item item;
	private int count;
	private int percentage;
}
