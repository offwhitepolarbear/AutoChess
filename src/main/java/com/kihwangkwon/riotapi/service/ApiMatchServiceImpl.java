package com.kihwangkwon.riotapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerChampion;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerTrait;
import com.kihwangkwon.businesslogic.match.service.MatchPlayerChampionRepository;
import com.kihwangkwon.businesslogic.match.service.MatchPlayerRepository;
import com.kihwangkwon.businesslogic.match.service.MatchPlayerTraitRepository;
import com.kihwangkwon.businesslogic.match.service.MatchRepository;
import com.kihwangkwon.riotapi.domain.ApiMatch;

public class ApiMatchServiceImpl implements ApiMatchService {
	
	private MatchRepository matchRepository;
	private MatchPlayerRepository matchPlayerRepository;
	private MatchPlayerChampionRepository matchPlayerChampionRepository;
	private MatchPlayerTraitRepository matchPlayerTraitRepository;

	@Autowired
	public ApiMatchServiceImpl(MatchRepository matchRepository, MatchPlayerRepository matchPlayerRepository,
			MatchPlayerChampionRepository matchPlayerChampionRepository,
			MatchPlayerTraitRepository matchPlayerTraitRepository) {

		this.matchRepository = matchRepository;
		this.matchPlayerRepository = matchPlayerRepository;
		this.matchPlayerChampionRepository = matchPlayerChampionRepository;
		this.matchPlayerTraitRepository = matchPlayerTraitRepository;
	}

	@Transactional
	public ApiMatch insertApiMatch(ApiMatch apiMatch) {
		Match match = apiMatch.getMatch();
		List<MatchPlayer> matchPlayerList = apiMatch.getMatchPlayerList();
		List<MatchPlayerChampion> matchPlayerChampionList = apiMatch.getMatchPlayerChampionList();
		List<MatchPlayerTrait> matchPlayerTraitList = apiMatch.getMatchPlayerTraitList();
		
		matchRepository.save(match);
		
		for(MatchPlayer player :matchPlayerList) {
			matchPlayerRepository.save(player);
		}
		for(MatchPlayerChampion champion:matchPlayerChampionList) {
			matchPlayerChampionRepository.save(champion);
		}
		for(MatchPlayerTrait trait:matchPlayerTraitList) {
			matchPlayerTraitRepository.save(trait);
		}
		return apiMatch;
	}

}
