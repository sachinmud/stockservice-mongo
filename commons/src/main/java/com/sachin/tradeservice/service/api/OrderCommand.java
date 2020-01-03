package com.sachin.tradeservice.service.api;

import io.eventuate.tram.commands.common.Command;

public abstract class OrderCommand implements Command {
	
	protected long stockOrderId;
	
	protected OrderCommand() {
	}

	protected OrderCommand(long stockOrderId) {
	    this.stockOrderId = stockOrderId;
	}

	public long getStockOrderId() {
		return stockOrderId;
	}

	public void setStockOrderId(long stockOrderId) {
		this.stockOrderId = stockOrderId;
	}
	
	

}
