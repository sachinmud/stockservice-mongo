package com.sachin.tradeservice.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JmsConfig {

    public static final String RETRIEVE_STOCK_QUEUE = "retrieve-stock-q";
    public static final String RETRIEVE_STOCK_RESPONSE_QUEUE = "retrieve-stock-response-q";
    public static final String UPDATE_PORTFOLIO_QUEUE = "update-portfolio-q";
    public static final String UPDATE_PORTFOLIO_RESPONSE_QUEUE = "update-portfolio-response-q";

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
	
}
