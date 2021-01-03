package com.sachin.tradeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.stockservice.model.StockModel;
import com.sachin.tradeservice.PropertiesConfig;

@Component
public class StockServiceProxy {

	private final RestTemplate restTemplate;

	@Autowired
	PropertiesConfig config;
	
	@Value("${stock.service.url}")
	private String stockServiceUrl;
	
	private static String restUserName = "admin";
	private static String restPassword = "password";
	
	public StockServiceProxy(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();
	}

	public StockModel getStockDetails(String code) {
		ResponseEntity<StockModel> stock = restTemplate.getForEntity(stockServiceUrl +"/v1/stock/{code}/quote", StockModel.class, code);
		return stock.getBody();
		
	}	
}
