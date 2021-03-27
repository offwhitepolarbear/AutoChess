package com.kihwangkwon.businesslogic.player.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.businesslogic.player.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
