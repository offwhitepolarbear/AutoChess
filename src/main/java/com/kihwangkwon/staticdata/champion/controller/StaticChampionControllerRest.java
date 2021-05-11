package com.kihwangkwon.staticdata.champion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.staticdata.champion.service.StaticChampionService;

@RequestMapping("/champion/rest")
@RestController
public class StaticChampionControllerRest {
	
	private StaticChampionService staticChampionService;
	
	@Autowired
	StaticChampionControllerRest(StaticChampionService staticChampionService){
		this.staticChampionService = staticChampionService;
	}
	
	@RequestMapping("/staticChampionList")
	public List getAllChampion() {
		return staticChampionService.staticChampionList();
	}
	
	@RequestMapping("/manager/insert/{version}")
	public void insertAllChampion(@PathVariable String version) {
		staticChampionService.insertStaticChampionAll(version);
	}
}
