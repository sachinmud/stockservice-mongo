package com.sachin.tradeservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.tradeservice.service.PortfolioServiceProxy;

@Component
public class PortfolioServiceProxyImpl {

	@Autowired
	PortfolioServiceProxy portfolioServiceProxy;
	
	public UserPortfolioModel getPortfolio(String userId) {
		
		Map<String, String> params = new HashMap<>();
		
		params.put("id", userId);
		
		UserPortfolioModel userPortfolio = portfolioServiceProxy.getPortfolio(userId);
		
		return userPortfolio;
	}


	public UserPortfolioModel savePortfolio(UserPortfolioModel portfolio) {
		
		UserPortfolioModel userPortfolio = portfolioServiceProxy.savePortfolio(portfolio);
		
		return userPortfolio;
	}
	
}
