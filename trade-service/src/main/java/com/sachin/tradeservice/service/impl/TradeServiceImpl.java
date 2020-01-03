package com.sachin.tradeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.service.TradeService;
import com.sachin.tradeservice.service.sagas.BuyStockSagaData;
import com.sachin.tradeservice.service.sagas.BuyStockSagaState;

import io.eventuate.tram.sagas.orchestration.SagaManager;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	private SagaManager<BuyStockSagaState> buyStockSagaManager;
	
	@Autowired
	StockTradeRepository repository;
	
	public StockOrderModel buyStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, new StockOrder());
		order.setAction('B');
		order = repository.save(order);
		orderVO = new EntityUtils<StockOrder, StockOrderModel>().copyProperties(order, orderVO);
		BuyStockSagaState data = new BuyStockSagaState(orderVO);
	    buyStockSagaManager.create(data);
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

}
