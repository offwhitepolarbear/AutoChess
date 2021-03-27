package com.kihwangkwon.businesslogic.match.service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.riotapi.domain.RegionNation;

public interface MatchService {
	public Match getMatch(RegionNation region, String matchId);
}
