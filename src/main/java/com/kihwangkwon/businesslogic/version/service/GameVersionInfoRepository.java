package com.kihwangkwon.businesslogic.version.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.version.domain.GameVersionInfo;

public interface GameVersionInfoRepository extends JpaRepository<GameVersionInfo, Long> {
}
