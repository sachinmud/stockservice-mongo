package com.sachin.portfolioservice.service;

import java.util.List;

import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.StockPortfolioModel;

public interface PortfolioService {
	
	public UserPortfolioModel getPortfolio(String id);
	
	public UserPortfolioModel getPortfoliobyUser(String userId);
	
	public UserPortfolioModel getPortfolioDetails(String id);
	
	public UserPortfolioModel getPortfolioDetailsbyUser(String userId);
	
	public StockPortfolioModel getStockPortfoliosByPortfolio(String portfolioId);
	
	public List<StockItemModel> getStockItemsByStockPortfolio(String stockPortfolioId);
	
	public UserPortfolioModel savePortfolio(UserPortfolioModel portfolioVO);
	
	public UserPortfolioModel updatePortfolio(UserPortfolioModel portfolioVO);
	
	public boolean deletePortfolio(String id);

}
