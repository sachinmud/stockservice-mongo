package com.sachin.stockservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class StockServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	@WithMockUser(authorities = {"user:read"})
	public void testGetStockDetails() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/stock/RELIANCE.NS/quote")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
