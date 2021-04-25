package com.kihwangkwon.businesslogic.match.service;

import java.util.List;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;

public interface MatchService {
	public Match getMatch(RegionNation region, String matchId);
	public Match getDBMatch(RegionNation region, String matchId);
	public List<Match> getMatchList(List<PlayerMatch> matchList);
	public List<MatchPlayer> getMatchPlayerListByPuuid(String puuid);
}
