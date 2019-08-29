package com.sachin.portfolioservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.portfolioservice.model.PortfolioModel;
import com.sachin.portfolioservice.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController()
@RequestMapping("v1/portfolio")
@Api(value = "Portfolio Service", description = "Get Portfolio Details")
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PortfolioModel getPortfolio(@PathVariable("id") String id) {
		return portfolioService.getPortfolio(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void savePortfolio(@RequestBody PortfolioModel portfolioVO) {
		portfolioService.savePortfolio(portfolioVO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void savePortfolio(@PathVariable("id") String id, @RequestBody PortfolioModel portfolioVO) {
		portfolioService.savePortfolio(portfolioVO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePortfolio(@PathVariable("id") String id) {
		portfolioService.deletePortfolio(id);
	}
	
}
