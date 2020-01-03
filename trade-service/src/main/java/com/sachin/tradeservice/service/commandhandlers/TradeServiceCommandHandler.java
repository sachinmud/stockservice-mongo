package com.sachin.tradeservice.service.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.sachin.tradeservice.service.TradeService;
import com.sachin.tradeservice.service.api.RejectOrderCommand;
import com.sachin.tradeservice.service.api.TradeServiceChannels;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class TradeServiceCommandHandler {
	
	@Autowired
	TradeService service;
	
	public CommandHandlers commandHandlers() {
	    return SagaCommandHandlersBuilder
	            .fromChannel(TradeServiceChannels.tradeServiceChannel)
	            .onMessage(RejectOrderCommand.class, this::rejectOrder)
	            .build();
	}
	
	public void rejectOrder(CommandMessage<RejectOrderCommand> cm) {
		service.deleteOrder(cm.getCommand().getStockOrderId());
	}
}
