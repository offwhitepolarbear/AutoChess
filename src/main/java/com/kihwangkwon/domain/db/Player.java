package com.kihwangkwon.domain.db;

import com.kihwangkwon.domain.enums.Tier;
import com.kihwangkwon.riotapi.domain.RegionNation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
	private String id;
	private String accountId;
	private String puuid;
	private String name;
	private RegionNation region;
	private String profileIconId;
	private String revisionDate;
	private int summonerLevel;
	private String server;
	private Tier tier;
	private String tierLevel;
	private String tierPoint;
}
