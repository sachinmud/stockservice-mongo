package com.sachin.tradeservice.sm.action;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.sachin.tradeservice.sm.constants.TradeEventsEnum;
import com.sachin.tradeservice.sm.constants.TradeStatusEnum;

@Component
@Qualifier("updatePortfolioFailAction")
public class UpdatePortfolioFailAction implements Action<TradeStatusEnum, TradeEventsEnum>{

    @Override
    public void execute(StateContext<TradeStatusEnum, TradeEventsEnum> context) {
    
    }

}
