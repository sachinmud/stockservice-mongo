package com.sachin.tradeservice.sm.manager.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import com.sachin.commons.util.EntityUtils;
import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.stockservice.model.StockModel;
import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.model.StockOrderModel;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.sm.constants.TradeConstants;
import com.sachin.tradeservice.sm.constants.TradeEventsEnum;
import com.sachin.tradeservice.sm.constants.TradeStatusEnum;
import com.sachin.tradeservice.sm.interceptor.TradeOrderStateChangeInterceptor;
import com.sachin.tradeservice.sm.manager.TradeManager;

@Service
public class TradeManagerImpl implements TradeManager {

	Logger logger = Logger.getLogger(TradeManagerImpl.class);
	
	@Autowired
	private StateMachineFactory<TradeStatusEnum, TradeEventsEnum> stateMachineFactory;
	
	@Autowired
	private TradeOrderStateChangeInterceptor tradeOrderStateChangeInterceptor;
	
	@Autowired
	private StockTradeRepository repository;

	public StockOrderModel buyStock(StockOrderModel orderVO) {
		
		StockOrder order = new EntityUtils<StockOrderModel, StockOrder>().copyProperties(orderVO, new StockOrder());
		order.setAction('B');
		order = repository.save(order);
		sendTradeEvent(order, TradeEventsEnum.RETRIEVE_STOCK);
        return new EntityUtils<StockOrder, StockOrderModel>().copyProperties(order, new StockOrderModel());
	    		
	}
	
	public void retrieveStockPassed(StockModel stock, String stockOrderId) {
        Optional<StockOrder> stockOrderOptional = repository.findById(Long.parseLong(stockOrderId));
        stockOrderOptional.ifPresent(stockOrder -> {
        	stockOrder.setName(stock.getName());
        	repository.save(stockOrder);
        	sendTradeEvent(stockOrder, TradeEventsEnum.RETRIEVE_STOCK_SUCCESS);
        	});
	}
	
    private void sendTradeEvent(StockOrder stockOrder, TradeEventsEnum eventEnum){
        StateMachine<TradeStatusEnum, TradeEventsEnum> sm = build(stockOrder);

        Message msg = MessageBuilder.withPayload(eventEnum)
                .setHeader(TradeConstants.ORDER_ID_HEADER, String.valueOf(stockOrder.getId()))
                .build();
        sm.sendEvent(msg);
    }
    
    private StateMachine<TradeStatusEnum, TradeEventsEnum> build(StockOrder order){
        StateMachine<TradeStatusEnum, TradeEventsEnum> sm = stateMachineFactory.getStateMachine(String.valueOf(order.getId()));

        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(tradeOrderStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(order.getStatus(), null, null, null));
                });

        sm.start();

        return sm;
    }
    
}
