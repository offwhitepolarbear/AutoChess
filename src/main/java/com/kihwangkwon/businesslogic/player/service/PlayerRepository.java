package com.kihwangkwon.businesslogic.player.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.player.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	public Player findByRegionAndName(String region, String Name);
	public Player findByRegionAndPuuid(String region, String puuid);
}
