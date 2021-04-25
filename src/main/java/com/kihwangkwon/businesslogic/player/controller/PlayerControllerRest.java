package com.kihwangkwon.businesslogic.player.controller;

import java.util.List;

import org.hibernate.annotations.ListIndexBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.businesslogic.player.domain.PlayerMatch;
import com.kihwangkwon.businesslogic.player.service.PlayerService;
import com.kihwangkwon.riotapi.domain.RegionNation;
@RequestMapping("/player/rest")
@RestController
public class PlayerControllerRest {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/{RegionNation}/{playerName}")
	public Player getPlayerByName(@PathVariable("RegionNation") String regionNation, @PathVariable("playerName") String playerName) {
		RegionNation region = RegionNation.valueOf(regionNation);
		Player player = playerService.getPlayerByName(region, playerName);
		return player;
	}
	@RequestMapping("/playerMatch/{RegionNation}/{puuid}")
	public List<PlayerMatch> getPlayerMatchList(@PathVariable("RegionNation") String regionNation, @PathVariable("puuid") String puuid){
		return playerService.getPlayerMatchListByPuuid(RegionNation.valueOf(regionNation), puuid);
	}
	
	
	@RequestMapping("/puuidList")
	public String getRecentMatch(@ModelAttribute List<Player> list) {
		//
		//List playerList = playerService.getPlayerByPuuid(region, playerName)
		return null;
	}

	@RequestMapping("/updatePlayer/{RegionNation}/{puuid}")
	public List<PlayerMatch> updatePlayerMatchList(@PathVariable("RegionNation") String regionNation, @PathVariable("puuid") String puuid){
		RegionNation region = RegionNation.valueOf(regionNation);
		List<PlayerMatch> playerMatchList = playerService.updatePlayerMatchList(region, puuid);
		return playerMatchList;
	}
	
	
}
