package com.kihwangkwon.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.player.service.PlayerService;
import com.kihwangkwon.riotapi.domain.RegionNation;

@RestController
public class PlayerControllerRest {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/player/{RegionNation}/{playerName}")
	public String getPlayerByName(@PathVariable("RegionNation") RegionNation region, @PathVariable("playerName") String playerName) {
		playerService.getPlayerByName(region, playerName);
		return null;
	}
	
	public String getRecentMatch() {
		return null;
	}
	
}
