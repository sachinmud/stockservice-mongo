package com.sachin.portfolioservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PortfolioServiceConfiguration {
	
	@Value("${signature}")
	private String signature;

	public String getSignature() {
		return signature;
	}
	
}
