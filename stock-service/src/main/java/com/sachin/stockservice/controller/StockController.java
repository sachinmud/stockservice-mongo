package com.sachin.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.service.StockService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="v1/stock")
@Api(value = "Stock Service", description = "Get Stock Details")
public class StockController {
	
	@Autowired
	StockService service;
	
	@PreAuthorize("hasAuthority('stock:read')")
	@GetMapping(value="/{code}/quote")
	public ResponseEntity<StockModel> getStockDetails(@PathVariable("code") String code) {
		
		return new ResponseEntity<StockModel>(service.getStock(code), HttpStatus.OK);
		
	}

}
