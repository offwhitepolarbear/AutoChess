package com.kihwangkwon.staticdata.trait.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.staticdata.trait.domain.StaticTrait;

public interface StaticTraitRepository extends JpaRepository<StaticTrait, Long> {

}
