package com.sachin.portfolioservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.portfolioservice.PortfolioServiceConfiguration;
import com.sachin.portfolioservice.domain.UserPortfolio;
import com.sachin.portfolioservice.domain.StockItem;
import com.sachin.portfolioservice.domain.StockPortfolio;
import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.StockPortfolioModel;
import com.sachin.portfolioservice.repository.PortfolioRepository;
import com.sachin.portfolioservice.repository.StockItemRepository;
import com.sachin.portfolioservice.repository.StockPortfolioRepository;
import com.sachin.portfolioservice.service.PortfolioService;
import com.sachin.portfolioservice.service.StockItemService;
import com.sachin.portfolioservice.service.StockPortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	Logger logger = Logger.getLogger(PortfolioServiceImpl.class);
	
	@Autowired
	PortfolioRepository portfolioRepository;
	
	@Autowired
	StockPortfolioRepository stockPortfolioRespository;
	
	@Autowired
	StockItemRepository stockItemRepository;
	
	@Autowired
	StockItemService stockItemService;
	
	@Autowired
	StockPortfolioService stockPortfolioService;
	
	@Autowired
	PortfolioServiceConfiguration config;
	
	public UserPortfolioModel getPortfoliobyUser(String userId) {
		
		UserPortfolioModel portfolioVO =  new UserPortfolioModel();
		try {
			UserPortfolio portfolio = portfolioRepository.findByUserId(userId);
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, new UserPortfolioModel());
		
		} catch(Exception e) {
			logger.error(e);
		}
		return portfolioVO;
	}
	
	public UserPortfolioModel getPortfolioDetailsbyUser(String userId) {
		
		UserPortfolioModel portfolioVO =  new UserPortfolioModel();
		try {
			UserPortfolio portfolio = portfolioRepository.findByUserId(userId);
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, new UserPortfolioModel());
			portfolioVO.setStockPortfolio(getStockPortfoliosByPortfolio(String.valueOf(portfolioVO.getId())));
			portfolioVO.getStockPortfolio().setStockItems(getStockItemsByStockPortfolio(String.valueOf(portfolioVO.getStockPortfolio().getId())));
		} catch(Exception e) {
			logger.error(e);
		}
		return portfolioVO;
	}

	public UserPortfolioModel getPortfolio(String portfolioId) {
		
		UserPortfolioModel portfolioVO =  new UserPortfolioModel();
		try {
			UserPortfolio portfolio = portfolioRepository.findById(portfolioId).get();
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, new UserPortfolioModel());
		
		} catch(Exception e) {
			logger.error(e);
		}
		return portfolioVO;
	}
	
	public UserPortfolioModel getPortfolioDetails(String portfolioId) {
		
		UserPortfolioModel portfolioVO =  new UserPortfolioModel();
		try {
			UserPortfolio portfolio = portfolioRepository.findById(portfolioId).get();
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, new UserPortfolioModel());
			logger.debug("Portfolio Id -->"+portfolioVO.getId());
			portfolioVO.setStockPortfolio(getStockPortfoliosByPortfolio(String.valueOf(portfolioVO.getId())));
			portfolioVO.getStockPortfolio().setStockItems(getStockItemsByStockPortfolio(String.valueOf(portfolioVO.getStockPortfolio().getId())));
			
		} catch(Exception e) {
			logger.error(e);
		}
		return portfolioVO;
	}
	
	public StockPortfolioModel getStockPortfoliosByPortfolio(String portfolioId) {
		
		StockPortfolioModel stockPortfolios = null;
		
		try {
			stockPortfolios = new EntityUtils<StockPortfolio, StockPortfolioModel>().copyProperties(stockPortfolioRespository.findByPortfolioId(new ObjectId(portfolioId)), new StockPortfolioModel());
		
		} catch(Exception e) {
			logger.error(e);
		}
		return stockPortfolios;
	}
	
	public List<StockItemModel> getStockItemsByStockPortfolio(String stockPortfolioId) {
		List<StockItemModel> stockItems = null;
		try {
			stockItems = stockItemRepository.findByStockPortfolioId(new ObjectId(stockPortfolioId)).stream().map(si -> new EntityUtils<StockItem, StockItemModel>().copyProperties(si, new StockItemModel())).collect(Collectors.toList());
		} catch(Exception e) {
			logger.error(e);
		}
		return stockItems;
	}

	public UserPortfolioModel savePortfolio(UserPortfolioModel portfolioVO) {
		
		try {
			final UserPortfolio portfolio = portfolioRepository.save(new EntityUtils<UserPortfolioModel, UserPortfolio>().copyProperties(portfolioVO, UserPortfolio.builder().build()));
			
			final StockPortfolio stockPortfolio = new EntityUtils<StockPortfolioModel, StockPortfolio>().copyProperties(portfolioVO.getStockPortfolio(), StockPortfolio.builder().build());
			stockPortfolio.setPortfolio(portfolio);
			stockPortfolioService.saveStockPortfolio(stockPortfolio);
			portfolioVO.getStockPortfolio().getStockItems().forEach(si -> {
				StockItem stockItem = new EntityUtils<StockItemModel, StockItem>().copyProperties(si, StockItem.builder().build());
				stockItem.setStockPortfolio(stockPortfolio);
				new EntityUtils<StockItem, StockItemModel>().copyProperties(stockItemService.saveStockItem(stockItem), si);
			});
			new EntityUtils<StockPortfolio, StockPortfolioModel>().copyProperties(stockPortfolio, portfolioVO.getStockPortfolio());
			portfolioVO.setSignature(config.getSignature());
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, portfolioVO);
		} catch(Exception e) {
			logger.error(e);
		}		
		return portfolioVO;
	}
	

	public UserPortfolioModel updatePortfolio(UserPortfolioModel portfolioVO) {
		
		try {
			final UserPortfolio portfolio = portfolioRepository.save(new EntityUtils<UserPortfolioModel, UserPortfolio>().copyProperties(portfolioVO, UserPortfolio.builder().build()));
			
			final StockPortfolio stockPortfolio = new EntityUtils<StockPortfolioModel, StockPortfolio>().copyProperties(portfolioVO.getStockPortfolio(), StockPortfolio.builder().build());
			stockPortfolio.setPortfolio(portfolio);
			stockPortfolioService.saveStockPortfolio(stockPortfolio);
			portfolioVO.getStockPortfolio().getStockItems().forEach(si -> {
				StockItem stockItem = new EntityUtils<StockItemModel, StockItem>().copyProperties(si, StockItem.builder().build());
				stockItem.setStockPortfolio(stockPortfolio);
				new EntityUtils<StockItem, StockItemModel>().copyProperties(stockItemService.saveStockItem(stockItem), si);
			});
			new EntityUtils<StockPortfolio, StockPortfolioModel>().copyProperties(stockPortfolio, portfolioVO.getStockPortfolio());
			portfolioVO.setSignature(config.getSignature());
			portfolioVO = new EntityUtils<UserPortfolio, UserPortfolioModel>().copyProperties(portfolio, portfolioVO);
		} catch(Exception e) {
			logger.error(e);
		}		
		return portfolioVO;

	}
	
	public boolean deletePortfolio(String id) {
		try {
			portfolioRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			logger.error(e);
		}
		return false;
	}
}
