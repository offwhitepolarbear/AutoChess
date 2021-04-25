package com.kihwangkwon.businesslogic.player.service;

import java.util.List;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.riotapi.domain.RegionNation;

public interface PlayerService {
	Player getPlayerByName(RegionNation region, String playerName);
	
	Player getPlayerByPuuid(RegionNation region, String puuid);
	
	List<PlayerMatch> getPlayerMatchListByPuuid(RegionNation region, String puuid);
	
	List<PlayerMatch> updatePlayerMatchList(RegionNation region, String puuid);
	
	List<Player> getPlayerListByPuuid(List<Player> playerList);
	
	// 외부api로 가져온 match 정보로  playerMatch 등록하기 전 기존에 등록된 playerMatch 조회용 
	List<PlayerMatch> getPlayerMatchListByMatchId(String matchId);
	
	List<PlayerMatch> updatePlayerMatchFromMatchApi(Match match, RegionNation region);
	
}
