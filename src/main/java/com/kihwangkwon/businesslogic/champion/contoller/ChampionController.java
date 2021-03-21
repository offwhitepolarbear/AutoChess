package com.kihwangkwon.businesslogic.champion.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChampionController {
	
	@RequestMapping("/champion")
	public String championMain() {
		return "/champion/Champion.html";
	}
}
