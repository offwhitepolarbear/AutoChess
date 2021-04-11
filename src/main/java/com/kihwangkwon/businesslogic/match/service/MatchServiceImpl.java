package com.kihwangkwon.businesslogic.match.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.businesslogic.player.service.PlayerService;
import com.kihwangkwon.riotapi.domain.RegionNation;
import com.kihwangkwon.riotapi.request.GetObjectFromApi;
import com.kihwangkwon.riotapi.request.RiotApiRequester;

@Service
public class MatchServiceImpl implements MatchService {
	
	private MatchRepository matchRepository;
	private GetObjectFromApi getObjectFromApi;
	private PlayerService playerService;
	
	@Autowired
	public MatchServiceImpl(MatchRepository matchRepository, GetObjectFromApi getObjectFromApi, PlayerService playerService) {
		this.matchRepository = matchRepository;
		this.getObjectFromApi = getObjectFromApi;
		this.playerService = playerService;
	}

	@Override
	public Match getMatch(RegionNation region, String matchId) {
		Match match = matchRepository.findByMatchId(matchId);
		if(match==null) {
			try {
				//api에서 match 정보 받아오기
				match = getObjectFromApi.getMatch(region, matchId);
				//match db에 저장
				match = matchRepository.save(match);
				
				//매치 정보에서 playerMatch 가공 후 db에 저장
				playerService.updatePlayerMatchFromMatchApi(match, region);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return match;
	}

	@Override
	public Match getDBMatch(RegionNation region, String matchId) {
		return matchRepository.findByMatchId(matchId);
	}
	
	@Override
	public List<Match> getMatchList(List<PlayerMatch> playerMatchList) {
		List<Match> matchList = null;
		if(playerMatchList!=null) {
			matchList = new ArrayList<Match>();
			for(PlayerMatch playerMatch : playerMatchList) {
				String region = playerMatch.getRegion();
				RegionNation nation = RegionNation.valueOf(region);
				String matchId = playerMatch.getMatchId();
				Match match = getDBMatch(nation, matchId);
				matchList.add(match);
			}
		}
		
		return matchList;
	}


}
