package com.kihwangkwon.riotapi.domain;

import java.util.List;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerChampion;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerTrait;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMatch {
	private Match match;
	private List<MatchPlayer> matchPlayerList;
	private List<MatchPlayerChampion> matchPlayerChampionList;
	private List<MatchPlayerTrait> matchPlayerTraitList;
}
