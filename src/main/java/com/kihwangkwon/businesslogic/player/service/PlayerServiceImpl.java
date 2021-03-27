package com.kihwangkwon.businesslogic.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.riotapi.domain.RegionNation;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	public PlayerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Player getPlayerByName(RegionNation region, String playerName) {
		
		return null;
	}
}
