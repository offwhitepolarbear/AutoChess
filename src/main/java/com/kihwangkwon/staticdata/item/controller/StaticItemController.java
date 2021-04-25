package com.kihwangkwon.staticdata.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kihwangkwon.staticdata.item.service.StaticItemService;
@RequestMapping("/item")
@Controller
public class StaticItemController {
	
	private StaticItemService staticItemService;
	
	@Autowired
	public StaticItemController(StaticItemService staticItemService) {
		this.staticItemService = staticItemService;
	}
	
}
