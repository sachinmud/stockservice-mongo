package com.sachin.tradeservice.service.commandhandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@Import({SagaParticipantConfiguration.class, SagaOrchestratorConfiguration.class})
public class TradeServiceCommandHandlerConfiguration {

	  @Bean
	  public TradeServiceCommandHandler tradeServiceCommandHandlers() {
	    return new TradeServiceCommandHandler();
	  }

	  @Bean
	  public SagaCommandDispatcher consumerCommandDispatcher(TradeServiceCommandHandler target, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) { return sagaCommandDispatcherFactory.make("tradeServiceDispatcher", target.commandHandlers()); }
	  
/*	  
	  @Bean
	  public SagaLockManager participantSagaLockManager() {
		  return new SagaLockManagerImpl(new EventuateSchema(EventuateSchema.EMPTY_SCHEMA));
	  }
*/
}
