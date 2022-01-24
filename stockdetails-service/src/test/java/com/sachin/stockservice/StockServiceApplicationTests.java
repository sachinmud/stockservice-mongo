package com.sachin.stockservice;

//import com.sachin.stockdetailsconsumerservice.StockdetailsconsumerServiceApplication;
//import com.sachin.stockdetailsprocessorservice.StockdetailsprocessorServiceApplication;
import com.sachin.stockservice.model.StockRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StockDetailsServiceApplication.class/*, StockdetailsprocessorServiceApplication.class, StockdetailsconsumerServiceApplication.class*/}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class StockServiceApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testStockQuotePost() throws Exception {

		StockRequest request = new StockRequest();
		request.setId("123456");
		request.setName("TCS.NS");
		request.setDatetime("2021-01-30 05:30");


		HttpEntity<StockRequest> entity = new HttpEntity<StockRequest>(request, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/stockdetailsservice/v1/stockQuote"),
				HttpMethod.POST, entity, String.class);

		String expected = "{id:Course1,name:Spring,description:10Steps}";


		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

		//JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private URI createURLWithPort(String uri) {
		return URI.create("http://localhost:" + port + uri);
	}

}
