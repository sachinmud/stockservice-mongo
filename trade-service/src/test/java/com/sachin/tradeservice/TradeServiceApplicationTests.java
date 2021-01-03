package com.sachin.tradeservice;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sachin.tradeservice.model.StockOrderModel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeServiceApplicationTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testBuyStock() throws Exception {
		
		StockOrderModel model = new StockOrderModel();
		model.setCode("RELIANCE.NS");
		model.setAction('B');
		model.setOrderDate(new Date());
		model.setPrice(new BigDecimal("1000.00"));
		model.setQuantity(10);
		model.setUserId(1);
		
		HttpEntity<StockOrderModel> entity = new HttpEntity<StockOrderModel>(model, headers);
		
		ResponseEntity<StockOrderModel> responseEntity = restTemplate.exchange(createURLWithPort("/buystock"), HttpMethod.POST, entity, StockOrderModel.class);
		
		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
