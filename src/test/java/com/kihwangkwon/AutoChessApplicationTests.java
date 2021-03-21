package com.kihwangkwon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kihwangkwon.businesslogic.champion.service.ChampionService;

@SpringBootTest
class AutoChessApplicationTests {
	@Autowired
	ChampionService championServiceImpl;
	@Test
	void contextLoads() {
		System.out.println("테스트");
		//championServiceImpl.addChampion();
		//championServiceImpl.addChampion(champion);
		System.out.println("테스트2");
	}

}
