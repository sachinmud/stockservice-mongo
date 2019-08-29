package com.sachin.portfolioservice.service;

import com.sachin.portfolioservice.model.PortfolioModel;

public interface PortfolioService {
	
	public PortfolioModel getPortfolio(String id);
	
	public void savePortfolio(PortfolioModel portfolioVO);
	
	public void updatePortfolio(PortfolioModel portfolioVO);
	
	public void deletePortfolio(String id);

}
