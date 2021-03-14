package com.kihwangkwon.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kihwangkwon.properties.ClassPathProperties;

@Controller
public class PlayerController {
	
	@RequestMapping("/player")
	public String playerMain() {
		return "/player/Player";
	}
	
	
}
