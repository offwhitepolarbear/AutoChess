package com.kihwangkwon.businesslogic.match.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.match.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
	public Match findByMatchId(String matchId);
}
