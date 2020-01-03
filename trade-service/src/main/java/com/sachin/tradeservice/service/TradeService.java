package com.sachin.tradeservice.service;

import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;

public interface TradeService {
	
	public StockOrderModel buyStock(StockOrderModel order);
	
	public StockOrderModel sellStock(StockOrderModel order);
	
	public boolean deleteOrder(long orderId);

}
