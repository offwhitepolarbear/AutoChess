package com.kihwangkwon.staticdata.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.staticdata.item.service.StaticItemService;

@RestController
@RequestMapping("/item/rest")
public class StaticItemControllerRest {
	private StaticItemService staticItemService;
	
	@Autowired
	public StaticItemControllerRest(StaticItemService staticItemService) {
		this.staticItemService = staticItemService;
	}
	
	@RequestMapping("/manager/insert")
	public void insertStaticItem() {
		staticItemService.insertStaticItemAll();
	}
	
	@RequestMapping("/itemList")
	public List itemList() {
		return staticItemService.staticItmeList();
	}
	
}
