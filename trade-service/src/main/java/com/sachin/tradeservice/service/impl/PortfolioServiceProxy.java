package com.sachin.tradeservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.tradeservice.PropertiesConfig;

@Component
public class PortfolioServiceProxy {

	RestTemplate restTemplate;
	
	@Autowired
	PropertiesConfig config;
	
	@Value("${portfolio.service.url}")
	private String portfolioServiceUrl;
	
	private static String restUserName = "admin";
	private static String restPassword = "password";
	
	public PortfolioServiceProxy(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();
	}
	
	
	public UserPortfolioModel getPortfolio(String userId) {
		
		Map<String, String> params = new HashMap<>();
		
		params.put("id", userId);
		
		ResponseEntity<UserPortfolioModel> restExchange = restTemplate.getForEntity(portfolioServiceUrl+"/v1/portfolio/user/{id}/details", UserPortfolioModel.class, userId);
		
		return restExchange.getBody();
	}


	public UserPortfolioModel savePortfolio(UserPortfolioModel portfolio) {
		
		ResponseEntity<UserPortfolioModel> restExchange = restTemplate.postForEntity(portfolioServiceUrl+"/v1/portfolio/", portfolio, UserPortfolioModel.class);
		
		return restExchange.getBody();
	}
	
}
