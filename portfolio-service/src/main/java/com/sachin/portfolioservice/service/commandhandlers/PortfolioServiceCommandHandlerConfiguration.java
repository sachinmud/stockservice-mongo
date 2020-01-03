package com.sachin.portfolioservice.service.commandhandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@Import({SagaParticipantConfiguration.class, SagaOrchestratorConfiguration.class, SagaParticipantConfiguration.class})
public class PortfolioServiceCommandHandlerConfiguration {
	  
	  @Bean
	  public PortfolioServiceCommandHandler portfolioServiceCommandHandlers() {
	    return new PortfolioServiceCommandHandler();
	  }

/*	  
	  @Bean
	  public SagaCommandDispatcher orderCommandHandlersDispatcher(TradeServiceCommandHandler tradeServiceCommandHandler, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
	    return sagaCommandDispatcherFactory.make("tradeService", tradeServiceCommandHandler.commandHandlers());
	  }
*/	  
	  @Bean
	  public SagaCommandDispatcher consumerCommandDispatcher(PortfolioServiceCommandHandler target, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) { return sagaCommandDispatcherFactory.make("portfolioServiceDispatcher", target.commandHandlers()); }
}
