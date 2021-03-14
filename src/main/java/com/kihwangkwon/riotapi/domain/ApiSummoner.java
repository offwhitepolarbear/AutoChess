package com.kihwangkwon.riotapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiSummoner {
	private String summonerId;
	private String summonerName;
	private int leaguePoints;
	private String rank;
	private int wins;
	private int losses;
	private boolean veteran;
	private boolean freshBlood;
	private boolean inactive;
	private boolean hotStreak;
}
