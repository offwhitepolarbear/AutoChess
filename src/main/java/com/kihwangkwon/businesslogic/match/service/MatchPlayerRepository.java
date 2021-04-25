package com.kihwangkwon.businesslogic.match.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;

public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {
	List<MatchPlayer> findByPuuid(String puuid,Sort sort);
}
