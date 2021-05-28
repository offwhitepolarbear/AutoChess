package com.kihwangkwon.staticdata.round.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.staticdata.round.service.RoundsServiceImpl;


@RestController
@RequestMapping("/round/rest")
public class RoundsController {
	
	private RoundsServiceImpl roundsServiceImpl;
	
	public RoundsController(RoundsServiceImpl roundsServiceImpl) {
		this.roundsServiceImpl = roundsServiceImpl;
	}
	
	@RequestMapping("/rounds/{version}")
	public int[] round(@PathVariable String version){
		
		return roundsServiceImpl.getRoundsCount(version);
	}
}
