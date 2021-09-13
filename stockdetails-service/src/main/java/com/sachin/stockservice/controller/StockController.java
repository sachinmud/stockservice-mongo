package com.sachin.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.service.StockService;

@RestController
@RequestMapping(value="v1/stock")
public class StockController {
	
	@Autowired
	StockService service;
	
	@RequestMapping(value="/{code}/quote",method = RequestMethod.GET)
	public StockModel getStock(@PathVariable("code") String code) {
		
		return service.getStock(code);
		
	}

}
