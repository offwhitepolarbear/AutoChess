package com.kihwangkwon.riotapi.domainstatic;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaticChampion {
	private String name;
	private String championId;
	private int cost;
	private String[] tarits;
	private String imageFileName;
}
