package com.kihwangkwon.businesslogic.match.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;

public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {

}
