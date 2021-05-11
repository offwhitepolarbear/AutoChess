package com.kihwangkwon.businesslogic.match.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.match.domain.MatchPlayerChampion;

public interface MatchPlayerChampionRepository extends JpaRepository<MatchPlayerChampion, Long> {
	
}
