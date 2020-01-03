package com.sachin.portfolioservice.service.api;

import com.sachin.portfolioservice.model.UserPortfolioModel;

public class GetPortfolioReply {
	
	private UserPortfolioModel portfolio;
	
	public GetPortfolioReply() {		
	}
	
	public GetPortfolioReply(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}

	public UserPortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}


}
