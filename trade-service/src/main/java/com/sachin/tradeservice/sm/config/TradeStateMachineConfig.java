package com.sachin.tradeservice.sm.config;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.sachin.tradeservice.sm.constants.TradeEventsEnum;
import com.sachin.tradeservice.sm.constants.TradeStatusEnum;

@Configuration
@EnableStateMachineFactory
public class TradeStateMachineConfig extends StateMachineConfigurerAdapter<TradeStatusEnum, TradeEventsEnum>
{
	@Autowired
	@Qualifier("retrieveStockAction")
	private Action<TradeStatusEnum, TradeEventsEnum> retrieveStockAction;
	
	@Autowired
	@Qualifier("updatePortfolioAction")
	private Action<TradeStatusEnum, TradeEventsEnum>  updatePortfolioAction;

	@Autowired
	@Qualifier("retrieveStockFailAction")
	private Action<TradeStatusEnum, TradeEventsEnum>  retrieveStockFailAction;
	
	@Autowired
	@Qualifier("updatePortfolioFailAction")
	private Action<TradeStatusEnum, TradeEventsEnum>  updatePortfolioFailAction;
	
    @Override
    public void configure(StateMachineStateConfigurer<TradeStatusEnum, TradeEventsEnum> states) throws Exception {
        states.withStates()
                .initial(TradeStatusEnum.NEW_ORDER)
                .states(EnumSet.allOf(TradeStatusEnum.class))
                .end(TradeStatusEnum.PORTFOLIO_UPDATED)
                .end(TradeStatusEnum.STOCK_RETRIEVE_FAIL)
                .end(TradeStatusEnum.STOCK_ORDER_FAIL)
                .end(TradeStatusEnum.PORTFOLIO_UPDATE_FAIL);
    }
    
    @Override
    public void configure(StateMachineTransitionConfigurer<TradeStatusEnum, TradeEventsEnum> transitions) throws Exception {
        transitions.withExternal()
        		.source(TradeStatusEnum.NEW_ORDER).target(TradeStatusEnum.STOCK_RETRIEVE_PENDING)
                .event(TradeEventsEnum.RETRIEVE_STOCK)
                .action(retrieveStockAction)
           .and().withExternal()
                .source(TradeStatusEnum.STOCK_RETRIEVE_PENDING).target(TradeStatusEnum.STOCK_RETRIEVED)
                .event(TradeEventsEnum.RETRIEVE_STOCK_SUCCESS)
           .and().withExternal()
                .source(TradeStatusEnum.STOCK_RETRIEVE_PENDING).target(TradeStatusEnum.STOCK_RETRIEVE_FAIL)
                .event(TradeEventsEnum.RETRIEVE_STOCK_FAIL)
                .action(retrieveStockFailAction)
           .and().withExternal()
                .source(TradeStatusEnum.STOCK_RETRIEVED).target(TradeStatusEnum.PORTFOLIO_UPDATE_PENDING)
                .event(TradeEventsEnum.UPDATE_PORTFOLIO)
                .action(updatePortfolioAction)
            .and().withExternal()
                .source(TradeStatusEnum.PORTFOLIO_UPDATE_PENDING).target(TradeStatusEnum.PORTFOLIO_UPDATED)
                .event(TradeEventsEnum.UPDATE_PORTFOLIO_SUCCESS)
            .and().withExternal()
                .source(TradeStatusEnum.PORTFOLIO_UPDATE_PENDING).target(TradeStatusEnum.PORTFOLIO_UPDATE_FAIL)
                .event(TradeEventsEnum.UPDATE_PORTFOLIO_FAIL)
                .action(updatePortfolioFailAction);
    }    
}
