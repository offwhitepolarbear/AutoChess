package com.kihwangkwon.staticdata.round.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rounds {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	
	private double tftSetNumberDetail;
	
	private int mainRound;
	private int subRound;
	private String roundDescription;	
}