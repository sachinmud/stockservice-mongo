package com.sachin.tradeservice.service.sagas;

import com.sachin.tradeservice.model.StockOrderModel;

public class BuyStockSagaData {
	
	private Long stockOrderId;
	
	private StockOrderModel stockOrder;
	
	public BuyStockSagaData() {}
	
	public BuyStockSagaData(StockOrderModel stockOrder) {
		this.stockOrder = stockOrder;
	}

	public Long getStockOrderId() {
		return stockOrderId;
	}

	public void setStockOrderId(Long stockOrderId) {
		this.stockOrderId = stockOrderId;
	}

	public StockOrderModel getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(StockOrderModel stockOrder) {
		this.stockOrder = stockOrder;
	}
	

}
