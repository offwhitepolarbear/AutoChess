package com.kihwangkwon.staticdata.trait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.staticdata.trait.service.StaticTraitService;
@RequestMapping("/trait/rest")
@RestController
public class StaticTraitContollerRest {
	
	private StaticTraitService staticTraitService;
	
	@Autowired
	StaticTraitContollerRest(StaticTraitService staticTraitService){
		this.staticTraitService = staticTraitService;
	}
	
	@RequestMapping("/manager/insert")
	public void insertTrait() {
		staticTraitService.insertStaticTraitAll();
	}
	
	@RequestMapping("/staticTraitList")
	public List traitList() {
		return staticTraitService.staticTraitList();
	}
}
