package com.sachin.tradeservice.service.api;

import com.sachin.tradeservice.model.StockOrderModel;

import io.eventuate.tram.commands.CommandDestination;
import io.eventuate.tram.commands.common.Command;

@CommandDestination("tradeService")
public class BuyStock implements Command {
	
	private StockOrderModel stockOrder;

	public StockOrderModel getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(StockOrderModel stockOrder) {
		this.stockOrder = stockOrder;
	}
	
	
}
