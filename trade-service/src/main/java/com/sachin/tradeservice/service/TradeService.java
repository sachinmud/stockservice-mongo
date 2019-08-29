package com.sachin.tradeservice.service;

import com.sachin.tradeservice.domain.StockOrder;

public interface TradeService {
	
	public StockOrder buyStock(StockOrder order);
	
	public StockOrder sellStock(StockOrder order);

}
