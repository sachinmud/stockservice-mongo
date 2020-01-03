package com.sachin.portfolioservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.portfolioservice.domain.StockPortfolio;
import com.sachin.portfolioservice.repository.StockPortfolioRepository;
import com.sachin.portfolioservice.service.StockPortfolioService;

@Service
public class StockPortfolioServiceImpl implements StockPortfolioService {

	@Autowired
	StockPortfolioRepository stockPortfolioRespository;
	
	public StockPortfolio saveStockPortfolio(StockPortfolio stockPortfolio) {
		return stockPortfolioRespository.save(stockPortfolio);
	}
}
