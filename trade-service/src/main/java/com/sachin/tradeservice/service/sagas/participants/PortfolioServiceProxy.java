package com.sachin.tradeservice.service.sagas.participants;

import com.sachin.portfolioservice.service.api.GetPortfolioCommand;
import com.sachin.portfolioservice.service.api.GetPortfolioReply;
import com.sachin.portfolioservice.service.api.PortfolioServiceChannels;
import com.sachin.portfolioservice.service.api.SavePortfolioCommand;
import com.sachin.portfolioservice.service.api.SavePortfolioReply;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

public class PortfolioServiceProxy {
	
/*	
	  public final CommandEndpoint<SavePortfolioCommand> save = CommandEndpointBuilder.forCommand(SavePortfolioCommand.class)
	          .withChannel(PortfolioServiceChannels.portfolioServiceChannel)
	          .withReply(SavePortfolioReply.class)
	          .build();	

	  public final CommandEndpoint<GetPortfolioCommand> get = CommandEndpointBuilder.forCommand(GetPortfolioCommand.class)
	          .withChannel(PortfolioServiceChannels.portfolioServiceChannel)
	          .withReply(GetPortfolioReply.class)
	          .build();
*/
}
