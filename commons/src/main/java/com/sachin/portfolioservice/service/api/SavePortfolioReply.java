package com.sachin.portfolioservice.service.api;

import com.sachin.portfolioservice.model.UserPortfolioModel;

public class SavePortfolioReply {
	
	private UserPortfolioModel portfolio;
	
	public SavePortfolioReply() {
	}
	
	public SavePortfolioReply(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}

	public UserPortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}


}
