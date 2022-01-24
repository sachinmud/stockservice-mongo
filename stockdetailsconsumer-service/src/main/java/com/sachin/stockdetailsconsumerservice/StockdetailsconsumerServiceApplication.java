package com.sachin.stockdetailsconsumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
public class StockdetailsconsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockdetailsconsumerServiceApplication.class, args);
	}

}
