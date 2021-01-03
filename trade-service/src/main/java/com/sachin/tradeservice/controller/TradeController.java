package com.sachin.tradeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.service.TradeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/v1/trade")
@Api(value = "Trade Service", description = "Get Trade Details")
public class TradeController {
	
	@Autowired
	TradeService service;
	
	@PostMapping(value = "/buystock")
	public ResponseEntity<StockOrderModel> buyStock(@RequestBody StockOrderModel order) {
		return new ResponseEntity<StockOrderModel>(service.buyStock(order), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/sellstock")
	public ResponseEntity<StockOrderModel> sellStock(@RequestBody StockOrderModel order) {
		return new ResponseEntity<StockOrderModel>(service.sellStock(order), HttpStatus.ACCEPTED);
	}	

}
