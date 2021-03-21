package com.kihwangkwon.staticdata.item.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kihwangkwon.common.StaticJsonParsing;
import com.kihwangkwon.staticdata.item.domain.StaticItem;

@Service
public class StaticItemServiceImpl implements StaticItemService{
	
	private StaticItemRepository staticItemRepository;
	private StaticJsonParsing staticJsonParsing;
	
	@Autowired
	public StaticItemServiceImpl(StaticItemRepository staticItemRepository, StaticJsonParsing staticJsonParsing) {
		this.staticItemRepository = staticItemRepository;
		this.staticJsonParsing = staticJsonParsing;
	}

	@Override
	public List staticItmeList() {
		// TODO Auto-generated method stub
		return staticItemRepository.findAll();
	}

	@Override
	public int insertStaticItemAll() {
		// TODO Auto-generated method stub
		List<HashMap> list = staticJsonParsing.itemList();
		for(HashMap hashMap : list) {
			insertStaticItem(hashMapToStaticItem(hashMap));
		}
		return 0;
	}

	@Override
	public int insertStaticItem(StaticItem staticItem) {
		staticItemRepository.save(staticItem);
		return 0;
	}
	
	private StaticItem hashMapToStaticItem(HashMap hashMap) {
		StaticItem staticItem = new StaticItem();
		String id = ((BigInteger)hashMap.get("id")).toString();
		String name = (String)hashMap.get("name");
		String description = (String)hashMap.get("description");
		staticItem.setItemId(id);
		staticItem.setName(name);
		staticItem.setDescrition(description);
		return staticItem;
	}
}
