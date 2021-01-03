package com.sachin.stockserviceweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:applicationconfig.properties")
public class PropertiesConfig {
	
	@Autowired
	private Environment env;

	public String getUserServiceUrl() {
		return env.getProperty("user.service.url");
	}

	public String getPortfolioServiceUrl() {
		return env.getProperty("portfolio.service.url");
	}
	
	public String getStockServiceUrl() {
		return env.getProperty("stock.service.url");
	}
}
