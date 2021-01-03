package com.sachin.portfolioservice.model.event;

import com.sachin.portfolioservice.model.UserPortfolioModel;

public class PortfolioResponse {
	
	private boolean errorFlag;
	
	private UserPortfolioModel portfolio;

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public UserPortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}
	
}
