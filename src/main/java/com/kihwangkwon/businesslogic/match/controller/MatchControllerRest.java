package com.kihwangkwon.businesslogic.match.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.service.MatchService;
import com.kihwangkwon.riotapi.domain.RegionNation;

@RequestMapping("/match/rest")
@RestController
public class MatchControllerRest {
	
	private MatchService matchService;
	
	@Autowired
	public MatchControllerRest(MatchService matchService) {
		this.matchService = matchService;
	}
	
	@RequestMapping("/searchMatch/{regionNation}/{matchId}")
	public Match searchMatch(@PathVariable("regionNation") RegionNation region, @PathVariable("matchId") String matchId) {
		return matchService.getMatch(region, matchId);
	}
}
