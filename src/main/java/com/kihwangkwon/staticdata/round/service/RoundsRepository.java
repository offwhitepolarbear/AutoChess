package com.kihwangkwon.staticdata.round.service;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.staticdata.round.domain.Rounds;

public interface RoundsRepository extends JpaRepository<Rounds, Long> {
	Collection<Rounds> findByTftSetNumberDetail(double setNumber, Sort sort);
}
