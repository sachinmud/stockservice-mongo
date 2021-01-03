package com.sachin.tradeservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.stockservice.model.StockModel;
import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	StockTradeRepository repository;
	
	@Autowired
	PortfolioServiceProxy portfolioClient;
	
	@Autowired
	StockServiceProxy stockClient;
	
	public StockOrderModel buyStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, new StockOrder());
		order.setAction('B');
		order = repository.save(order);
		
		StockModel stock = stockClient.getStockDetails(order.getCode());
		
		UserPortfolioModel portfolio =  portfolioClient.getPortfolio(String.valueOf(orderVO.getUserId()));
		
		portfolio = modifyPortfolioForBuy(portfolio, order, stock);
		portfolio = portfolioClient.savePortfolio(portfolio);
		orderVO = new EntityUtils<StockOrder, StockOrderModel>().copyProperties(order, orderVO);
	    return new EntityUtils<StockOrder, StockOrderModel>().copyProperties(repository.findById(order.getId()).get(), new StockOrderModel());		
	}
	
	public StockOrderModel sellStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, new StockOrder());
		order.setAction('S');		
		return new EntityUtils<StockOrder, StockOrderModel>().copyProperties(repository.save(order), new StockOrderModel());
		
	}

	public boolean deleteOrder(long orderId) {
		repository.deleteById(orderId);
		return true;
	}
	
	private UserPortfolioModel modifyPortfolioForBuy(UserPortfolioModel portfolio, StockOrder order, StockModel stock) {
		List<StockItemModel> stockItems = portfolio.getStockPortfolio().getStockItems();
		boolean found = false;
		for(StockItemModel si: stockItems) {
			if(si.getCode().equalsIgnoreCase(order.getCode())) {
				si.setBuyPrice(((si.getBuyPrice().multiply(new BigDecimal(si.getQuantity()))).add((order.getPrice().multiply(new BigDecimal(order.getQuantity()))))).divide(new BigDecimal(si.getQuantity()+order.getQuantity())));
				si.setQuantity(si.getQuantity()+order.getQuantity());
				found = true;
				break;
			}
		}
		if(!found) {
			StockItemModel si = new StockItemModel();
			si.setCode(order.getCode());
			si.setName(stock.getName());
			si.setBuyPrice(order.getPrice());
			si.setQuantity(order.getQuantity());
			if(stockItems == null) {
				stockItems = new ArrayList<StockItemModel>();
			}
			stockItems.add(si);
		}
		portfolio.getStockPortfolio().setStockItems(stockItems);
		
		return portfolio;
		
	}

}
