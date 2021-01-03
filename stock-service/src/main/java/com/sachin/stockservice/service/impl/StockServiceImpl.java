package com.sachin.stockservice.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.stockservice.domain.Stock;
import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.repository.StockRepository;
import com.sachin.stockservice.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	Logger logger = Logger.getLogger(StockServiceImpl.class);
			
	@Autowired
	@Qualifier("yahoofinance")
	private StockRepository stockRepository;

	@Override
	public StockModel getStock(String code) {

		StockModel stockModel = new StockModel();
		try {
			Stock stock = stockRepository.selectStock(code);
			stockModel = new EntityUtils<Stock, StockModel>().copyProperties(stock, stockModel);
		} catch (Exception e) {
			
		}
		return stockModel;
	}

}
