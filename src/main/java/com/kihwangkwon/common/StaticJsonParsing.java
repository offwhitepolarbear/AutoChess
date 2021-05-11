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
	private final String championPath = "/static/file/sets/";
	private final String traitPath = "/static/file/sets/";
	private final String itemPath = "/static/file/sets/";
	
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
	
	public List championList(String version) {
		return jsonToObjectList(championPath+version+"/champions.json");
	}

	public List traitList(String version) {
		return jsonToObjectList(traitPath+version+"/traits.json");
	}
	
	public List itemList(String version) {
		return jsonToObjectList(itemPath+version+"/items.json");
	}
	
	public String versionConvert(String version) {
		StringBuffer result = new StringBuffer();
		/////3.5 4.5 버전 뒤에 .5 빼기 위한 작업
		if(version.contains(".")) {
			//정규표현식 스플릿이라 그냥.으로 나눌경우 작동안함
			String[] mainVersion = version.split("[.]");
			version = mainVersion[0];
		}
		
		//0이 나왔을 때 이전에 1~9가 나온적이 있나 확인하는 boolean
		boolean isNothingBeforeZero = true;
		
		//0안나올때까지 자르다가 나머지를...
		char[] versionArray = version.toCharArray();
		
		for(char splitChar : versionArray) {
			if(splitChar!='0') {
				//0아닌게 나왔을때 무조건 true 로 바뀌게
				isNothingBeforeZero = false;
				result.append(splitChar);
			}
			
			if(splitChar=='0') {
				//0아닌게 나오는 이후에 나오는 0은 더해줘야 함 ex) 시즌 010 이면 0 다 제끼게 하면 시즌 1이됨
				if(!isNothingBeforeZero) {
					result.append(splitChar);
				}
			}
		}
		return result.toString();
	}
	
}
