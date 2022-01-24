package com.sachin.stockdetailsconsumerservice.kafka;

import com.sachin.stock.StockDetailsEvent;
import com.sachin.stockdetailsconsumerservice.repository.StockDetailsRepository;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventMessageConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(EventMessageConsumerConfig.class);

    @Autowired
    StockDetailsRepository repository;

    @Bean
    public Consumer<KStream<String, StockDetailsEvent>> eventMessageConsumer() {
        return kStream -> kStream.foreach((k,v) -> {logger.info(String.format("Event created -> %s", v));
            repository.save(v);
        });
    }
}
