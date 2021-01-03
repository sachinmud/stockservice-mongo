package com.sachin.stockservice.model.event;

import com.sachin.stockservice.model.StockModel;

public class StockResponse {

	private String correlationId;
	
	private boolean errorFlag;

	private StockModel stock;
	
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public StockModel getStock() {
		return stock;
	}

	public void setStock(StockModel stock) {
		this.stock = stock;
	}
	
}
