package com.sachin.tradeservice.service.sagas;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.portfolioservice.model.StockItemModel;
import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.portfolioservice.service.api.GetPortfolioCommand;
import com.sachin.portfolioservice.service.api.GetPortfolioReply;
import com.sachin.portfolioservice.service.api.SavePortfolioCommand;
import com.sachin.portfolioservice.service.api.SavePortfolioReply;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.service.api.RejectOrderCommand;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

import io.eventuate.tram.commands.consumer.CommandWithDestination;

public class BuyStockSagaState {
	
	private long stockOrderId;
	
	private StockOrderModel stockOrder;
	
	private UserPortfolioModel portfolio;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean update = false;
	
	public BuyStockSagaState() {}
	
	public BuyStockSagaState(StockOrderModel stockOrder) {
		this.stockOrder = stockOrder;
		this.stockOrderId = stockOrder.getOrderId();
	}
	
	public BuyStockSagaState(Long stockOrderId, StockOrderModel stockOrder) {
		
		this.stockOrderId = stockOrderId;
		this.stockOrder = stockOrder;
	}

	public long getStockOrderId() {
		return stockOrderId;
	}

	public void setStockOrderId(long stockOrderId) {
		this.stockOrderId = stockOrderId;
	}

	public StockOrderModel getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(StockOrderModel stockOrder) {
		this.stockOrder = stockOrder;
	}
	
	public UserPortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolioModel portfolio) {
		this.portfolio = portfolio;
	}
	
	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public RejectOrderCommand makeRejectOrderCommand() {
	    return new RejectOrderCommand(getStockOrderId());
	}
	
	public void handleSavePortfolioReply(SavePortfolioReply reply) {
	    logger.debug("Portfolio Id in the reply :", reply.getPortfolio().getId());
	}
	
	public CommandWithDestination getPortfolioCommand() {
		logger.debug("Get Portfolio for User Id :"+ getStockOrder().getUserId());
		System.out.println("Get Portfolio for User Id :"+ getStockOrder().getUserId());
		return send(new GetPortfolioCommand(getStockOrder().getUserId())).to("portfolioservice").build();
	}
	
	public void handleGetPortfolioReply(GetPortfolioReply reply) {
		this.portfolio = reply.getPortfolio();
	}
	
	public CommandWithDestination savePortfolioCommand() {
		setUpdate(false);
		portfolio.getStockPortfolio().getStockItems().forEach(si -> {
			
			if(si.getCode().equals(getStockOrder().getCode())) {
				si.setQuantity(si.getQuantity()+getStockOrder().getQuantity());
				si.setBuyDate(getStockOrder().getOrderDate());
				si.setBuyPrice(si.getBuyPrice().add(getStockOrder().getPrice()).divide(new BigDecimal(si.getQuantity())));
				setUpdate(true);
			}
			
		});
		
		if(!isUpdate()) {
			StockItemModel si = new StockItemModel();
			si.setCode(getStockOrder().getCode());
			si.setQuantity(getStockOrder().getQuantity());
			si.setBuyDate(getStockOrder().getOrderDate());
			si.setBuyPrice(getStockOrder().getPrice());
			ArrayList<StockItemModel> stockItems = new ArrayList<>();
			stockItems.add(si);
			portfolio.getStockPortfolio().setStockItems(stockItems);
		}
		return send(new SavePortfolioCommand(portfolio)).to("portfolioservice").build();
		
	}
}
