package com.kihwangkwon.staticdata.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kihwangkwon.staticdata.item.service.StaticItemService;
@RequestMapping("/item/rest")
@Controller
public class StaticItemController {
	
	private StaticItemService staticItemService;
	
	@Autowired
	public StaticItemController(StaticItemService staticItemService) {
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
