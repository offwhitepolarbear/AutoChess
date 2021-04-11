package com.kihwangkwon.riotapi.domainconvert;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;
@Service
public class ConvertCommonUtil {
	
	
	
	int objectToInt(Object object) {
		return ((BigInteger)object).intValue();
	}
	
	Long objectToLong(Object object) {
		return ((BigInteger)object).longValue();
	}
	
	String objectNumberToString(Object object) {
		Long longString = objectToLong(object);
		return longString.toString();
	}
	
	Timestamp obejectToTimestamp(Object object) {
		Timestamp timestamp = new Timestamp(objectToLong(object));
		return timestamp;
	}
	
}
