package com.kihwangkwon.businesslogic.player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
@Controller
public class PlayerController {
	
	@RequestMapping("")
	public String playerMain() {
		return "player/Player";
	}
	
}
