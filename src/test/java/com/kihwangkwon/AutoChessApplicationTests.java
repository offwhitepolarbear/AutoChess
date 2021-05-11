package com.kihwangkwon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kihwangkwon.businesslogic.champion.service.ChampionService;
import com.kihwangkwon.businesslogic.player.service.PlayerService;
import com.kihwangkwon.riotapi.domain.RegionNation;

@SpringBootTest
class AutoChessApplicationTests {
	@Autowired
	private PlayerService playerService;
	@Test
	void contextLoads() {
		//playerService.updatePlayerMatchList(RegionNation.kr, "tgtKCbSPQ3T28z6_8uSpSWAc3M3w2ZQ6xDM4TWFGJqHdBX991oWEp7RN0O8QHQYAQ-h8MiqXIHKNdg");
	}

}
