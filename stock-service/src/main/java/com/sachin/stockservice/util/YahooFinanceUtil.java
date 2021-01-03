package com.sachin.stockservice.util;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class YahooFinanceUtil {
	
	static Logger logger = Logger.getLogger(YahooFinanceUtil.class);
	
	public static com.sachin.stockservice.domain.Stock getStockDetails(String code) {
		com.sachin.stockservice.domain.Stock model =  new com.sachin.stockservice.domain.Stock();
		
		try {
			
			Stock stock = YahooFinance.get(code);
			model.setName(stock.getName());
			model.setCode(stock.getSymbol());
			model.setValueDate(stock.getQuote().getLastTradeTime().getTime());
			model.setOpen(stock.getQuote().getOpen());
			model.setClose(stock.getQuote().getPrice());
			model.setLow(stock.getQuote().getDayLow());
			model.setHigh(stock.getQuote().getDayHigh());
			model.setAdjClose(stock.getQuote().getChange());
			model.setVolume(stock.getQuote().getVolume());
			
		} catch(Exception e) {
			logger.error(e);
		}

		return model;
	}
 
}
