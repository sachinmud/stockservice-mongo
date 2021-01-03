package com.sachin.tradeservice.jms.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.sachin.stockservice.model.event.StockResponse;
import com.sachin.tradeservice.jms.config.JmsConfig;
import com.sachin.tradeservice.sm.manager.TradeManager;

@Component
public class RetrieveStockListener {
	
	Logger logger = Logger.getLogger(RetrieveStockListener.class);
	@Autowired
	private TradeManager tradeManager;
	
    @JmsListener(destination = JmsConfig.RETRIEVE_STOCK_RESPONSE_QUEUE)
    public void listen(StockResponse result){
        if(!result.isErrorFlag()){
        	tradeManager.retrieveStockPassed(result.getStock(), result.getCorrelationId());
        } else {
        	logger.info("Stock Retrieval failed");
        }
    }
	

}
