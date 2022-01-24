package com.sachin.stockservice.kafka;

import com.sachin.stock.StockDetailsEvent;
import com.sachin.stockservice.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventMessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(EventMessageConsumer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${stockevent.kafka.stream.topic}")
    private String topic;

    @KafkaListener(topics = "${stockevent.kafka.topic}", groupId = AppConstants.GROUP_ID)
    public void consume(StockDetailsEvent event) {
        logger.info(String.format("Event created -> %s", event));
        kafkaTemplate.send(topic, event);
    }


}
