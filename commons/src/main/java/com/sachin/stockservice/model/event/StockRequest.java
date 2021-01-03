package com.sachin.stockservice.model.event;

import com.sachin.stockservice.model.StockModel;

public class StockRequest {

	private String code;
	
	private String correlationId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
}
