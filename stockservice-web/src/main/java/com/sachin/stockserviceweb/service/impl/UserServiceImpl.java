package com.sachin.stockserviceweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sachin.stockserviceweb.PropertiesConfig;
import com.sachin.stockserviceweb.service.UserService;
import com.sachin.userservice.model.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	PropertiesConfig config;
	
	@Value("${user.service.url}")
	private String userServiceUrl;
	
	private static String restUserName = "admin";
	private static String restPassword = "password";
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();    
		ResponseEntity<UserModel> user = restTemplate.getForEntity(userServiceUrl +"/v1/user/name/{username}", UserModel.class, username);
		
		return user.getBody();
		
	}
	
	public List<UserModel> getAllUsers() {
		
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();    
		ResponseEntity<List> users = restTemplate.getForEntity(config.getUserServiceUrl() +"/v1/user/all", List.class);
		
		return users.getBody();

	}
	
	public UserModel getUser(Long userId) {
		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();    
		ResponseEntity<UserModel> user = restTemplate.getForEntity(config.getUserServiceUrl() +"/v1/user/{userId}", UserModel.class, userId);
		
		return user.getBody();
		
	}

}
