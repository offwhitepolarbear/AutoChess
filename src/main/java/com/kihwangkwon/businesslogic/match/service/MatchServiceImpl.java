package com.kihwangkwon.businesslogic.match.service;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.riotapi.domain.RegionNation;
import com.kihwangkwon.riotapi.request.GetObjectFromApi;
import com.kihwangkwon.riotapi.request.RiotApiRequester;

@Service
public class MatchServiceImpl implements MatchService {
	
	private MatchRepository matchRepository;
	private GetObjectFromApi getObjectFromApi;
	
	@Autowired
	public MatchServiceImpl(MatchRepository matchRepository, GetObjectFromApi getObjectFromApi) {
		this.matchRepository = matchRepository;
		this.getObjectFromApi = getObjectFromApi;
	}

	@Override
	public Match getMatch(RegionNation region, String matchId) {
		Match match = matchRepository.findByMatchId(matchId);
		if(match==null) {
			try {
				match = getObjectFromApi.getMatch(region, matchId);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return match;
	}
}
