package com.sachin.stockservice.service;

import com.sachin.stockservice.model.StockEvent;
import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.model.StockRequest;

public interface StockService {
	
	public StockModel getStock(String code);

	public StockEvent postStockEvent(StockRequest request) throws Exception;
	

}
