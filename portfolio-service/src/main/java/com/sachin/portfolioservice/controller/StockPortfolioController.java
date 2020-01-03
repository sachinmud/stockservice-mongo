package com.sachin.portfolioservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.portfolioservice.model.StockPortfolioModel;
import com.sachin.portfolioservice.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController()
@RequestMapping("v1/stockportfolio")
@Api(value = "Stock Portfolio Service", description = "Get Stock Portfolio Details")
public class StockPortfolioController {
	
	@Autowired
	PortfolioService service;

	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.GET)
	public StockPortfolioModel getStockPortfolioByPortfolio(@PathVariable("id") String id) {
		return service.getStockPortfoliosByPortfolio(id);
	}	
}
