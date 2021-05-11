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
	public int insertStaticItemAll(String version) {
		// TODO Auto-generated method stub
		List<HashMap> list = staticJsonParsing.itemList(version);
		for(HashMap hashMap : list) {
			insertStaticItem(hashMapToStaticItem(hashMap, version));
		}
		return 0;
	}

	@Override
	public int insertStaticItem(StaticItem staticItem) {
		staticItemRepository.save(staticItem);
		return 0;
	}
	
	private StaticItem hashMapToStaticItem(HashMap hashMap, String version) {
		StaticItem staticItem = new StaticItem();
		
		String tftSetNumber = staticJsonParsing.versionConvert(version);
		
		String id = ((BigInteger)hashMap.get("id")).toString();
		String name = (String)hashMap.get("name");
		String description = (String)hashMap.get("description");
		
		staticItem.setTftSetNumber(tftSetNumber);

		staticItem.setItemId(id);
		staticItem.setName(name);
		staticItem.setDescrition(description);
		staticItem.setTftSetNumber(version);
		return staticItem;
	}
	
	private String versionConvert(String version) {
		StringBuffer modifiedVersion = new StringBuffer();
		char[] versionArray = version.toCharArray();
		for (int i=0;i<versionArray.length;i++) {
			if (versionArray[i]!='0') {
				break;
			}
			else {
				
			}
		}
		
		return version;
	}
}
