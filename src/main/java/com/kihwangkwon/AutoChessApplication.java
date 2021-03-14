package com.kihwangkwon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AutoChessApplication {
	
	public static final String APPLICATION_LOCATIONS =
			"spring.config.location="
			+"C:\\sts-4.5.1.RELEASE\\yml\\AutoChess\\application.yml"
			+","
			+"C:\\sts-4.5.1.RELEASE\\yml\\AutoChess\\riot.yml"
			+","
			+"classpath:/application.yml";
	public static void main(String[] args) {
		new SpringApplicationBuilder(AutoChessApplication.class)
		.properties(APPLICATION_LOCATIONS)
		.run(args);
	}
	
}
