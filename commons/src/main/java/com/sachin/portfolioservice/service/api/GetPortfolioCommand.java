package com.sachin.portfolioservice.service.api;

import io.eventuate.tram.commands.common.Command;

public class GetPortfolioCommand implements Command {
	
	private long userId;
	
	public GetPortfolioCommand(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
