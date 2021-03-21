package com.kihwangkwon.common;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

@Service
public class StaticJsonParsing {
	private final String championPath = "/file/current_set/champions.json";
	private final String traitPath = "/file/current_set/traits.json";
	private final String itemPath = "/file/current_set/items.json";
	
	public List jsonToObjectList(String jsonPath) {
		JSONParser jsonParser;
		InputStream is = getClass().getResourceAsStream(jsonPath);
		Reader reader = new InputStreamReader(is);
		jsonParser = new JSONParser(reader);
		List<Object> list = null;
		try {
			list = jsonParser.parseArray();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List championList() {
		return jsonToObjectList(championPath);
	}

	public List traitList() {
		return jsonToObjectList(traitPath);
	}
	
	public List itemList() {
		return jsonToObjectList(itemPath);
	}
}
