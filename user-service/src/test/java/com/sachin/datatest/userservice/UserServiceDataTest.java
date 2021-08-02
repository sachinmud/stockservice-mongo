package com.sachin.datatest.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sachin.userservice.AbstractIntegrationTest;
import com.sachin.userservice.domain.User;
import com.sachin.userservice.repository.UserRepository;

public class UserServiceDataTest extends AbstractIntegrationTest {
	
	@Autowired
	UserRepository repos;
	

	@Test
	public void testGetUser() {
		User user = repos.findByUsername("admin");
		assertEquals("admin", user.getUsername());
	}

}
