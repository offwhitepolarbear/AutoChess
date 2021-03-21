package com.kihwangkwon.staticdata.item.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
public class StaticItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String itemId;
	private String name;
	private String descrition;
}
