package com.sachin.portfolioservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController()
@RequestMapping("v1/stockitem")
@Api(value = "Stock Item Service", description = "Get Stock Item Details")
public class StockItemController {

	@Autowired
	PortfolioService service;

	@RequestMapping(value = "/stockportfolio/{id}", method = RequestMethod.GET)
	public List<StockItemModel> getStockItemsByStockPortfolio(@PathVariable("id") String id) {
		return service.getStockItemsByStockPortfolio(id);
	}		
}
