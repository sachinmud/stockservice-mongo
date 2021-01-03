package com.sachin.tradeservice.sm.action;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.sachin.stockservice.model.event.StockRequest;
import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.jms.config.JmsConfig;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.sm.constants.TradeConstants;
import com.sachin.tradeservice.sm.constants.TradeEventsEnum;
import com.sachin.tradeservice.sm.constants.TradeStatusEnum;

@Component
@Qualifier("retrieveStockAction")
public class RetrieveStockAction implements Action<TradeStatusEnum, TradeEventsEnum>{

	Logger logger = Logger.getLogger(RetrieveStockAction.class);
			
	@Autowired
	private StockTradeRepository repository;
	
	@Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<TradeStatusEnum, TradeEventsEnum> context) {

        String stockOrderId = (String) context.getMessage().getHeaders().get(TradeConstants.ORDER_ID_HEADER);
        Optional<StockOrder> stockOrderOptional = repository.findById(Long.parseLong(stockOrderId));

        stockOrderOptional.ifPresent(stockOrder -> {
            StockRequest request = new StockRequest();
            request.setCode(stockOrder.getCode());
            request.setCorrelationId(String.valueOf(stockOrder.getId()));
            jmsTemplate.convertAndSend(JmsConfig.RETRIEVE_STOCK_QUEUE, request);
            
        });

        logger.debug("Sent stock retrieval request for id " + stockOrderId);
    }

}
