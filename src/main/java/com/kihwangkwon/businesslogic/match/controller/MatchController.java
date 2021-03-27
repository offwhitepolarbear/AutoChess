package com.kihwangkwon.businesslogic.match.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kihwangkwon.businesslogic.match.service.MatchService;

@RequestMapping("/match")
@Controller
public class MatchController {
	private MatchService matchService;
	
	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@RequestMapping("/")
	public String matchMain() {
		return "/match/Match.html";
	}
}
