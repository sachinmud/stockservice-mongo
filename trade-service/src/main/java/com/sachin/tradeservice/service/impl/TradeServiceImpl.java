package com.sachin.tradeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	StockTradeRepository repository;
	
	public StockOrder buyStock(StockOrder order) {
		
		order.setAction('B');		
		return repository.save(order);
		
	}
	
	public StockOrder sellStock(StockOrder order) {
		
		order.setAction('S');		
		return repository.save(order);
		
	}
	

}
