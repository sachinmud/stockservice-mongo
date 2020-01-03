package com.sachin.portfolioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.common.DefaultChannelMapping;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableEurekaClient
@EnableSwagger2
@Import({PortfolioServiceConfiguration.class,
    TramEventsPublisherConfiguration.class,
    TramCommandProducerConfiguration.class,
    SagaOrchestratorConfiguration.class,
    TramJdbcKafkaConfiguration.class})
public class PortfolioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioServiceApplication.class, args);
	}

	  @Bean
	  public ChannelMapping channelMapping() {
	    return DefaultChannelMapping.builder().build();
	  }

	@Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
         .apis(RequestHandlerSelectors.basePackage("com.sachin.portfolioservice.controller")).paths(PathSelectors.regex("/.*")).build();
   }	

}
