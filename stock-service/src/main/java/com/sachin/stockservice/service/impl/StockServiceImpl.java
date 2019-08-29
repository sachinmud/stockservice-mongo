package com.sachin.stockservice.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.stockservice.domain.Stock;
import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.repository.StockRepository;
import com.sachin.stockservice.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	Logger logger = Logger.getLogger(StockServiceImpl.class);
			
	@Autowired
	private StockRepository stockRepository;

	@Override
	public StockModel getStock(String code) {
		
		StockModel model =  new StockModel();
		try {
			Stock stock = stockRepository.selectStock(code);
			model.setCode(code);
			model.setValueDate(stock.getValueDate());
			model.setOpen(stock.getOpen());
			model.setClose(stock.getClose());
			model.setLow(stock.getLow());
			model.setHigh(stock.getHigh());
			model.setAdjClose(stock.getAdjClose());
			model.setVolume(stock.getVolume());
			
		} catch(Exception e) {
			logger.error(e);
		}
		
		return model;
	}

}
