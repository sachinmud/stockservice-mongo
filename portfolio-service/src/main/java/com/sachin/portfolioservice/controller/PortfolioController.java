package com.sachin.portfolioservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.portfolioservice.model.StockPortfolioModel;
import com.sachin.portfolioservice.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController()
@RequestMapping("v1/portfolio")
@Api(value = "Portfolio Service", description = "Get Portfolio Details")
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserPortfolioModel getPortfolio(@PathVariable("id") String id) {
		return portfolioService.getPortfolio(id);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public UserPortfolioModel getPortfolioByUser(@PathVariable("id") String id) {
		return portfolioService.getPortfoliobyUser(id);
	}

	@RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
	public UserPortfolioModel getPortfolioDetails(@PathVariable("id") String id) {
		return portfolioService.getPortfolioDetails(id);
	}
	
	@RequestMapping(value = "/user/{id}/details", method = RequestMethod.GET)
	public UserPortfolioModel getPortfolioDetailsByUser(@PathVariable("id") String id) {
		return portfolioService.getPortfolioDetailsbyUser(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public UserPortfolioModel savePortfolio(@RequestBody UserPortfolioModel portfolioVO) {
		return portfolioService.savePortfolio(portfolioVO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserPortfolioModel savePortfolio(@PathVariable("id") String id, @RequestBody UserPortfolioModel portfolioVO) {
		return portfolioService.savePortfolio(portfolioVO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deletePortfolio(@PathVariable("id") String id) {
		return portfolioService.deletePortfolio(id);
	}
	
}
