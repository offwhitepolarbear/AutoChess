package com.kihwangkwon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AutoChessApplication {
	

		
	public static void main(String[] args) {
		String propertiesLocation = getPropertiesLocation();
		
		new SpringApplicationBuilder(AutoChessApplication.class)
		.properties(propertiesLocation)
		.run(args);
	}
	
	//개발환경이랑 실제 서버 환경 프로퍼티 위치 확인용 메서드
	private static String getPropertiesLocation() {
		
		String propertiesLocation = null;
		
		final String windowPropertiesLocation =
				"spring.config.location="
				+"C:\\sts-4.5.1.RELEASE\\yml\\AutoChess\\application.yml"
				+","
				+"C:\\sts-4.5.1.RELEASE\\yml\\AutoChess\\riot.yml"
				+","
				+"classpath:/application.yml";
		
		final String linuxPorpertiesLocation = 
				"spring.config.location="
				+ "/home/ec2-user/app/00config/autochess/application.yml"
				+","
				+ "/home/ec2-user/ap0config/autochess/riot.yml"
				+","
				+"classpath:/application.yml";
				;
		
		String os =System.getProperty("os.name");

		if(os.contains("Windows")) {
			propertiesLocation = windowPropertiesLocation;
		}
		if(os.contains("Linux")) {
			propertiesLocation = linuxPorpertiesLocation;
		}
		
		return propertiesLocation;
	}

}
