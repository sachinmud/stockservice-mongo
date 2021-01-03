package com.sachin.portfolioservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sachin.portfolioservice.PropertiesConfig;
import com.sachin.userservice.model.UserModel;


@Service
public class UserServiceProxy implements UserDetailsService {

	RestTemplate restTemplate;
	
	@Autowired
	PropertiesConfig config;
	
	@Value("${user.service.url}")
	private String userServiceUrl;
	
	private static String restUserName = "admin";
	private static String restPassword = "password";
	
	public UserServiceProxy(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ResponseEntity<UserModel> userModel = restTemplate.getForEntity(userServiceUrl+"/v1/user/name/{name}", UserModel.class, username);
		return userModel.getBody();
		
	}
	
	
}
