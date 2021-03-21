package com.kihwangkwon.staticdata.item.service;

import java.util.List;

import com.kihwangkwon.staticdata.item.domain.StaticItem;


public interface StaticItemService {
	public int insertStaticItemAll();
	public int insertStaticItem(StaticItem staticItem);
	public List staticItmeList();
}
