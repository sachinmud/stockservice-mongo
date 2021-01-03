package com.sachin.tradeservice.sm.manager;

import com.sachin.stockservice.model.StockModel;
import com.sachin.tradeservice.model.StockOrderModel;

public interface TradeManager {

	public StockOrderModel buyStock(StockOrderModel orderVO);
	
	public void retrieveStockPassed(StockModel stock, String stockOrderId);
}
