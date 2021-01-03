package com.sachin.tradeservice.sm.interceptor;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.tradeservice.domain.StockOrder;
import com.sachin.tradeservice.repository.StockTradeRepository;
import com.sachin.tradeservice.sm.constants.TradeConstants;
import com.sachin.tradeservice.sm.constants.TradeEventsEnum;
import com.sachin.tradeservice.sm.constants.TradeStatusEnum;

@Component
public class TradeOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<TradeStatusEnum, TradeEventsEnum> {

	@Autowired
    private StockTradeRepository tradeRepository;
	
	Logger logger = Logger.getLogger(TradeOrderStateChangeInterceptor.class);

    @Transactional
    @Override
    public void preStateChange(State<TradeStatusEnum, TradeEventsEnum> state, Message<TradeEventsEnum> message, Transition<TradeStatusEnum, TradeEventsEnum> transition, StateMachine<TradeStatusEnum, TradeEventsEnum> stateMachine) {
    	logger.debug("Pre-State Change");

        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(TradeConstants.ORDER_ID_HEADER, " ")))
                .ifPresent(orderId -> {
                	logger.debug("Saving state for order id: " + orderId + " Status: " + state.getId());

                    StockOrder stockOrder = tradeRepository.getOne(Long.parseLong(orderId));
                    stockOrder.setStatus(state.getId());
                    tradeRepository.saveAndFlush(stockOrder);
                });
    }	
}
