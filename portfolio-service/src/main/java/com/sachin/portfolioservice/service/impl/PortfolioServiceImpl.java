package com.sachin.portfolioservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.portfolioservice.domain.Portfolio;
import com.sachin.portfolioservice.domain.StockItem;
import com.sachin.portfolioservice.domain.StockPortfolio;
import com.sachin.portfolioservice.model.PortfolioModel;
import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.StockPortfolioModel;
import com.sachin.portfolioservice.repository.PortfolioRepository;
import com.sachin.portfolioservice.repository.StockItemRepository;
import com.sachin.portfolioservice.repository.StockPortfolioRepository;
import com.sachin.portfolioservice.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	Logger logger = Logger.getLogger(PortfolioServiceImpl.class);
	
	@Autowired
	PortfolioRepository portfolioRepository;
	
	@Autowired
	StockPortfolioRepository stockPortfolioRespository;
	
	@Autowired
	StockItemRepository stockItemRepository;
	
	public PortfolioModel getPortfolio(String id) {
		
		PortfolioModel portfolioVO =  new PortfolioModel();
		try {
			StockPortfolioModel stockPortfolioVO = null;
			StockItemModel stockItemVO = null;
			List<StockPortfolioModel> stockPortfolioVOs = new ArrayList<>();
			List<StockItemModel> stockItemVOs = new ArrayList<>();
			Portfolio portfolio = portfolioRepository.findById(Long.parseLong(id)).get();
			BeanUtils.copyProperties(portfolio, portfolioVO);
			
/*			
			List<StockPortfolio> stockPortfolios = portfolio.getStockPortfolio();
			for(StockPortfolio stockPortfolio:stockPortfolios) {
				stockPortfolioVO = new StockPortfolioModel();
				BeanUtils.copyProperties(stockPortfolio, stockPortfolioVO);
				List<StockItem> stockItems = stockPortfolio.getStockItems();
				for(StockItem stockItem:stockItems) {
					stockItemVO = new StockItemModel();
					BeanUtils.copyProperties(stockItem, stockItemVO);
					stockItemVOs.add(stockItemVO);
				}
				stockPortfolioVO.setStockItems(stockItemVOs);
				stockPortfolioVOs.add(stockPortfolioVO);
			}
			portfolioVO.setStockPortfolio(stockPortfolioVOs);	
								*/
		} catch(Exception e) {
			logger.error(e);
			
		}
		
		return portfolioVO;
	}
	
	public void savePortfolio(PortfolioModel portfolioVO) {
		
		try {
			Portfolio portfolio = new Portfolio();
			List<StockPortfolio> stockPortfolios = new ArrayList<>();
			List<StockItem> stockItems = new ArrayList<>();
			StockPortfolio stockPortfolio = null;
			StockItem stockItem = null;
			BeanUtils.copyProperties(portfolioVO, portfolio);
			portfolio = portfolioRepository.save(portfolio);
			List<StockPortfolioModel> stockPortfolioVOs = portfolioVO.getStockPortfolio();
			for(StockPortfolioModel stockPortfolioVO : stockPortfolioVOs) {
				stockPortfolio = new StockPortfolio();
				BeanUtils.copyProperties(stockPortfolioVO, stockPortfolio);
				stockPortfolio.setPortfolio(portfolio);
				stockPortfolio = stockPortfolioRespository.save(stockPortfolio);
				List<StockItemModel> stockItemVOs = stockPortfolioVO.getStockItems();
				for(StockItemModel stockItemVO:stockItemVOs) {
					stockItem = new StockItem();
					BeanUtils.copyProperties(stockItemVO, stockItem);
					stockItem.setStockPortfolio(stockPortfolio);
					stockItems.add(stockItem);
				}
				stockItems = stockItemRepository.saveAll(stockItems);
				stockPortfolios.add(stockPortfolio);
			}
		} catch(Exception e) {
			logger.error(e);
		}
	}

	public void updatePortfolio(PortfolioModel portfolioVO) {
		try {
			Portfolio portfolio = new Portfolio();
			List<StockPortfolio> stockPortfolios = new ArrayList<>();
			List<StockItem> stockItems = new ArrayList<>();
			StockPortfolio stockPortfolio = null;
			StockItem stockItem = null;
			BeanUtils.copyProperties(portfolioVO, portfolio);
			portfolio = portfolioRepository.save(portfolio);
			List<StockPortfolioModel> stockPortfolioVOs = portfolioVO.getStockPortfolio();
			for(StockPortfolioModel stockPortfolioVO : stockPortfolioVOs) {
				stockPortfolio = new StockPortfolio();
				BeanUtils.copyProperties(stockPortfolioVO, stockPortfolio);
				stockPortfolio.setPortfolio(portfolio);
				stockPortfolio = stockPortfolioRespository.save(stockPortfolio);
				List<StockItemModel> stockItemVOs = stockPortfolioVO.getStockItems();
				for(StockItemModel stockItemVO:stockItemVOs) {
					stockItem = new StockItem();
					BeanUtils.copyProperties(stockItemVO, stockItem);
					stockItem.setStockPortfolio(stockPortfolio);
					stockItems.add(stockItem);
				}
				stockItems = stockItemRepository.saveAll(stockItems);
				stockPortfolios.add(stockPortfolio);
			}
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	public void deletePortfolio(String id) {
		try {
			portfolioRepository.deleteById(Long.parseLong(id));
		} catch(Exception e) {
			logger.error(e);
		}
		
	}
}
