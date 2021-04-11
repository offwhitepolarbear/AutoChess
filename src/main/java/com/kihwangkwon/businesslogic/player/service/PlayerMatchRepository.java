package com.kihwangkwon.businesslogic.player.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
	List<PlayerMatch> findByPuuid(String puuid);
	List<PlayerMatch> findByMatchId(String matchId);
}
