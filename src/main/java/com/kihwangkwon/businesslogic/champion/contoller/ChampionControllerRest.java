package com.kihwangkwon.businesslogic.champion.contoller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kihwangkwon.common.GetDataFromExternalApi;
import com.kihwangkwon.properties.ClassPathProperties;
import com.kihwangkwon.properties.ConstructorProperties;

@EnableConfigurationProperties(value= {ConstructorProperties.class})
@RestController
@RequestMapping("/champion")
public class ChampionControllerRest {
	
	@Autowired
	ConstructorProperties constructorProperties;
	
	@Autowired
	GetDataFromExternalApi getDataFromExternalApi;
	
}
