package com.sachin.stockservice.kafka;

import com.sachin.stock.StockDetailsEvent;
import com.sachin.stockservice.model.StockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventMessageProducer {

    private Logger log = LoggerFactory.getLogger(EventMessageProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${stockevent.kafka.topic}")
    private String topic;

    public void sendMessage(StockDetailsEvent message) {
        log.info("MESSAGE SENT FROM PRODUCER END -> " + message);
        kafkaTemplate.send(topic, message);
    }
}
