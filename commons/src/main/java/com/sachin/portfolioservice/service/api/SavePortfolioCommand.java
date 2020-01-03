package com.sachin.portfolioservice.service.api;

import com.sachin.portfolioservice.model.UserPortfolioModel;

public class SavePortfolioCommand extends PortfolioCommand {
	
	public SavePortfolioCommand(UserPortfolioModel portfolio) {
		super(portfolio);
	}

}
