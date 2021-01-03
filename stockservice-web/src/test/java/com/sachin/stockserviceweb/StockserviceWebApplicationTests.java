package com.sachin.stockserviceweb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sachin.stockserviceweb.service.UserService;
import com.sachin.userservice.model.UserModel;

@SpringBootTest
class StockserviceWebApplicationTests {

	@Autowired
	UserService service;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testGetUser( ) {
		UserModel user = service.getUser(Long.valueOf(1));
		assertNotNull(user);
	}

}
