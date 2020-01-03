package com.sachin.portfolioservice.service.api;

import com.sachin.portfolioservice.model.UserPortfolioModel;

import io.eventuate.tram.commands.common.Command;

public class PortfolioCommand implements Command {
	
	private UserPortfolioModel portfolio;
	
	protected PortfolioCommand(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}

	public UserPortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}
	
	

}
