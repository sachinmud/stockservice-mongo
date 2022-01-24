package com.sachin.stockdetailsprocessorservice.kafka;

import com.sachin.stock.StockDetailsEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@EnableAutoConfiguration
public class EventMessageProcessorConfig {

    private final Logger logger = LoggerFactory.getLogger(EventMessageProcessorConfig.class);

    @Bean
    public Function<KStream<String, StockDetailsEvent>, KStream<String, StockDetailsEvent>> eventMessageProcessor() {
        return kStream -> kStream.filter((k,v) -> v.getName() != null).peek((k,v) -> logger.info(v.getName()));
    }
}
