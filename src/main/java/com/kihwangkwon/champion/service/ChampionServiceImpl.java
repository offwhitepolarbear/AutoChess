package com.kihwangkwon.champion.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.kihwangkwon.domain.db.Champion;
@Service
public class ChampionServiceImpl implements ChampionService {

	@Override
	public int addChampion(Champion champion) {
		String jsonPath = "/file/current_set/champions.json";
		System.out.println("호출됨");
			JSONParser jsonParser;
				InputStream is = getClass().getResourceAsStream(jsonPath);
				Reader reader = new InputStreamReader(is);
				jsonParser = new JSONParser(reader);
				JSONArray jsonObject;
				try {
					List<Object> list = jsonParser.parseArray();
					System.out.println(list);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return 0;
	}


	@Override
	public List getChampionList() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
