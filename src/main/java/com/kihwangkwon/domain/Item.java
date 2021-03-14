package com.kihwangkwon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.kihwangkwon.domain.enums.ItemAbility;
import com.kihwangkwon.domain.enums.ItemType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class Item {
	//단일 아이템을 할건지
	//합성 아이템을 나눌건지 어떻게 하는게 맞는지 궁금하네;
	//@Column
	private String itemName;
	//@Column
	private String itemNameKorean;
	//@Column
	private ItemAbility[] itemAbilities;
	private ItemType itemType;
	private int[] amounts;
	
}
