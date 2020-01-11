package com.sachin.tradeservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.portfolioservice.model.UserPortfolioModel;

@Component
public class PortfolioServiceProxy {

	@Autowired
	RestTemplate restTemplate;
	
	public UserPortfolioModel getPortfolio(String userId) {
		
		Map<String, String> params = new HashMap<>();
		
		params.put("id", userId);
		
		ResponseEntity<UserPortfolioModel> restExchange = restTemplate.exchange("http://portfolioserviceconfig/v1/portfolio/user/{id}/details", HttpMethod.GET, null, UserPortfolioModel.class, userId);
		
		return restExchange.getBody();
	}


	public UserPortfolioModel savePortfolio(UserPortfolioModel portfolio) {
		
		ResponseEntity<UserPortfolioModel> restExchange = restTemplate.postForEntity("http://portfolioserviceconfig/v1/portfolio/", portfolio, UserPortfolioModel.class);
		
		return restExchange.getBody();
	}
	
}
