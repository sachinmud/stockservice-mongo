package com.sachin.stockservice;

import com.sachin.stockservice.api.StockQuoteApiDelegate;
import com.sachin.stockservice.delegate.impl.StockQuoteApiDelegateImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableKafka
@EnableSwagger2
@SpringBootApplication
public class StockDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDetailsServiceApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sachin.stockservice.api")).paths(PathSelectors.regex("/.*")).build();
	}

}
