package com.kihwangkwon.businesslogic.champion.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kihwangkwon.domain.db.Champion;
@Service
public class ChampionServiceImpl implements ChampionService {

	@Override
	public int addChampion(Champion champion) {
		/*
		String jsonPath = "/file/current_set/champions.json";
			JSONParser jsonParser;
				InputStream is = getClass().getResourceAsStream(jsonPath);
				Reader reader = new InputStreamReader(is);
				jsonParser = new JSONParser(reader);
				Gson gson = new Gson();
				gson.fromJson(json, classOfT)
				//jsonObject;
				try {
					List<Object> list = jsonParser.parseArray();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/	
		return 0;
	}


	@Override
	public List getChampionList() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
