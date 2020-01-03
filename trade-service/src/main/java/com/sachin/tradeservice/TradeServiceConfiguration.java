package com.sachin.tradeservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sachin.tradeservice.service.sagas.BuyStockSaga;
import com.sachin.tradeservice.service.sagas.BuyStockSagaState;
import com.sachin.tradeservice.service.sagas.participants.PortfolioServiceProxy;
import com.sachin.tradeservice.service.sagas.participants.TradeServiceProxy;

import io.eventuate.tram.sagas.common.SagaLockManager;
import io.eventuate.tram.sagas.common.SagaLockManagerImpl;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;

@Configuration
public class TradeServiceConfiguration {
	
	  @Bean
	  public SagaManager<BuyStockSagaState> buyStockSagaManager(BuyStockSaga saga) {
	    return new SagaManagerImpl<>(saga);
	  }

	  @Bean
	  public BuyStockSaga buyStockSaga(TradeServiceProxy tradeService, PortfolioServiceProxy portfolioService) {
	    return new BuyStockSaga(tradeService, portfolioService);
	  }
	  
	  @Bean
	  public TradeServiceProxy tradeServiceProxy() {
		  return new TradeServiceProxy();
	  }
	  
	  @Bean
	  public PortfolioServiceProxy portfolioServiceProxy() {
		  return new PortfolioServiceProxy();
	  }
	  
/*
	  @Bean
	  public SagaLockManager commonsSagaLockManager( ) {
		  return new SagaLockManagerImpl(new EventuateSchema(EventuateSchema.EMPTY_SCHEMA));
	  }
*/	  
}

