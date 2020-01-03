package com.sachin.tradeservice.service.sagas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.portfolioservice.service.api.GetPortfolioReply;
import com.sachin.portfolioservice.service.api.SavePortfolioReply;
import com.sachin.tradeservice.service.sagas.participants.PortfolioServiceProxy;
import com.sachin.tradeservice.service.sagas.participants.TradeServiceProxy;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

public class BuyStockSaga implements SimpleSaga<BuyStockSagaState> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SagaDefinition<BuyStockSagaState> sagaDefinition;
	
	public BuyStockSaga() {
		
	}
	
	  @Override
	  public SagaDefinition<BuyStockSagaState> getSagaDefinition() {
	    return sagaDefinition;
	  }
	  
	  public BuyStockSaga(TradeServiceProxy tradeService, PortfolioServiceProxy portfolioService) {
		  logger.debug("Inside BuyStockSaga");
		  System.out.println("Inside BuyStockSaga");
			this.sagaDefinition =
			  step()
			   .withCompensation(tradeService.reject, BuyStockSagaState::makeRejectOrderCommand)
			   .step()
			   .invokeParticipant(BuyStockSagaState::getPortfolioCommand)
			   .onReply(GetPortfolioReply.class, BuyStockSagaState::handleGetPortfolioReply)
			   .step()
			   .invokeParticipant(BuyStockSagaState::savePortfolioCommand)
			   .onReply(SavePortfolioReply.class, BuyStockSagaState::handleSavePortfolioReply)
			 .build();
	
	  }	  
	

}
