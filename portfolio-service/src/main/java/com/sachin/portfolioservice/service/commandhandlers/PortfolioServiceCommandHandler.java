package com.sachin.portfolioservice.service.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.sachin.portfolioservice.service.PortfolioService;
import com.sachin.portfolioservice.service.api.GetPortfolioCommand;
import com.sachin.portfolioservice.service.api.GetPortfolioReply;
import com.sachin.portfolioservice.service.api.PortfolioServiceChannels;
import com.sachin.portfolioservice.service.api.SavePortfolioCommand;
import com.sachin.portfolioservice.service.api.SavePortfolioReply;

import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class PortfolioServiceCommandHandler {
	
	@Autowired
	PortfolioService service;
	
	public CommandHandlers commandHandlers() {
	    return SagaCommandHandlersBuilder
	            .fromChannel(PortfolioServiceChannels.portfolioServiceChannel)
	            .onMessage(GetPortfolioCommand.class, this::getPortfolioByUserId)
	            .onMessage(SavePortfolioCommand.class, this::savePortfolio)
	            .build();
	  }
	
	private GetPortfolioReply getPortfolioByUserId(CommandMessage<GetPortfolioCommand> cm) {
		System.out.println("getPortfolioByUserId-->");
		return new GetPortfolioReply(service.getPortfoliobyUser(String.valueOf(cm.getCommand().getUserId())));
	}

	private Message savePortfolio(CommandMessage<SavePortfolioCommand> cm) {
		try {
			return CommandHandlerReplyBuilder.withSuccess(new SavePortfolioReply(service.savePortfolio(cm.getCommand().getPortfolio())));
			
		} catch(Exception e) {
			return CommandHandlerReplyBuilder.withFailure((new SavePortfolioReply(service.savePortfolio(cm.getCommand().getPortfolio()))));
		}
	}
}
