package com.sachin.stockservice.service.impl;

import com.sachin.stock.StockDetailsEvent;
import com.sachin.stockservice.kafka.EventMessageProducer;
import com.sachin.stockservice.model.StockEvent;
import com.sachin.stockservice.model.StockRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.stockservice.domain.Stock;
import com.sachin.stockservice.model.StockModel;
import com.sachin.stockservice.repository.StockRepository;
import com.sachin.stockservice.service.StockService;
import yahoofinance.YahooFinance;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class StockServiceImpl implements StockService {
	
	Logger logger = Logger.getLogger(StockServiceImpl.class);
			
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private EventMessageProducer producer;

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

	@Override
	public StockEvent postStockEvent(StockRequest request) throws Exception {

		logger.info(request);
		yahoofinance.Stock stock = YahooFinance.get(request.getName());

		StockEvent event = new StockEvent();
		event.setDatetime(LocalDate.now().toString());
		event.setName(request.getName());
		event.setId(UUID.randomUUID().toString());

		StockDetailsEvent detailsEvent = StockDetailsEvent.builder().id(event.getId()).datetime(event.getDatetime())
						.name(event.getName()).price(stock.getQuote().getPrice()).volume(stock.getQuote().getVolume())
						.high(stock.getQuote().getDayHigh()).low(stock.getQuote().getDayLow())
						.open(stock.getQuote().getOpen()).close(stock.getQuote().getPrice())
						.adjClose(stock.getQuote().getPrice()).build();
		producer.sendMessage(detailsEvent);
		logger.info(detailsEvent);
		return event;
	}
}
