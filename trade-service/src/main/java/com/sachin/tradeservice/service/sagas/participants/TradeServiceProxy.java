package com.sachin.tradeservice.service.sagas.participants;

import com.sachin.tradeservice.service.api.RejectOrderCommand;
import com.sachin.tradeservice.service.api.TradeServiceChannels;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

public class TradeServiceProxy {
	
	  public final CommandEndpoint<RejectOrderCommand> reject = CommandEndpointBuilder
	          .forCommand(RejectOrderCommand.class)
	          .withChannel(TradeServiceChannels.tradeServiceChannel)
	          .withReply(Success.class)
	          .build();	

}
