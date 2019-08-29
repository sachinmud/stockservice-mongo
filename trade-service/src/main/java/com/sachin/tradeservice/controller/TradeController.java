package com.sachin.tradeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.service.TradeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/v1/trade")
@Api(value = "Trade Service", description = "Get Trade Details")
public class TradeController {
	
	@Autowired
	TradeService service;
	
	@RequestMapping(value = "/buystock")
	public StockOrder buyStock(@RequestBody StockOrder order) {
		return service.buyStock(order);
	}
	
	@RequestMapping(value = "/sellstock")
	public StockOrder sellStock(@RequestBody StockOrder order) {
		return service.sellStock(order);
	}	

}
