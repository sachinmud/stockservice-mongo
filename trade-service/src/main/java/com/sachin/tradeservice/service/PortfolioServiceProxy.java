package com.sachin.tradeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sachin.portfolioservice.model.UserPortfolioModel;

@FeignClient(name = "${feign.portfolioservice.name}", url = "${feign.portfolioservice.url}")
public interface PortfolioServiceProxy {
	
	@GetMapping("/v1/portfolio/user/{id}/details")
	public UserPortfolioModel getPortfolio(@PathVariable("id") String userId);
	
	@PostMapping("/v1/portfolio/")
	public UserPortfolioModel savePortfolio(@RequestBody UserPortfolioModel userPortfolio);

}
