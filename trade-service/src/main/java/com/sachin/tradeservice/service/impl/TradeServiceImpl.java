package com.sachin.tradeservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	StockTradeRepository repository;
	
	@Autowired
	PortfolioServiceProxyImpl portfolioClient;
	
	public StockOrderModel buyStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, StockOrder.builder().build());
		order.setAction('B');
		order = repository.save(order);
		UserPortfolioModel portfolio =  portfolioClient.getPortfolio(String.valueOf(orderVO.getUserId()));
		
		portfolio = modifyPortfolioForBuy(portfolio, order);
		portfolio = portfolioClient.savePortfolio(portfolio);
		orderVO = new EntityUtils<StockOrder, StockOrderModel>().copyProperties(order, orderVO);
	    return new EntityUtils<StockOrder, StockOrderModel>().copyProperties(repository.findById(order.getId()).get(), new StockOrderModel());		
	}
	
	public StockOrderModel sellStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, StockOrder.builder().build());
		order.setAction('S');		
		return new EntityUtils<StockOrder, StockOrderModel>().copyProperties(repository.save(order), new StockOrderModel());
		
	}

	public boolean deleteOrder(String orderId) {
		repository.deleteById(orderId);
		return true;
	}
	
	private UserPortfolioModel modifyPortfolioForBuy(UserPortfolioModel portfolio, StockOrder order) {
		List<StockItemModel> stockItems = portfolio.getStockPortfolio().getStockItems();
		boolean found = false;
		for(StockItemModel si: stockItems) {
			if(si.getCode().equalsIgnoreCase(order.getCode())) {
				si.setBuyPrice(((si.getBuyPrice().multiply(new BigDecimal(si.getQuantity()))).add((order.getPrice().multiply(new BigDecimal(order.getQuantity()))))).divide(new BigDecimal(si.getQuantity()+order.getQuantity()),2, RoundingMode.HALF_UP));
				si.setQuantity(si.getQuantity()+order.getQuantity());
				found = true;
				break;
			}
		}
		if(!found) {
			StockItemModel si = new StockItemModel();
			si.setCode(order.getCode());
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
