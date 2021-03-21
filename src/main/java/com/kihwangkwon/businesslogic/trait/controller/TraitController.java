package com.kihwangkwon.businesslogic.trait.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TraitController {
	@RequestMapping("/trait")
	public String trait() {
		return "/trait/Trait.html";
	}
}
