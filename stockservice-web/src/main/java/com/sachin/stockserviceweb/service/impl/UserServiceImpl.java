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
	
	private final RestTemplate restTemplate;
	
	@Autowired
	PropertiesConfig config;
	
	@Value("${user.service.url}")
	private String userServiceUrl;
	
	private static String restUserName = "admin";
	private static String restPassword = "password";
	
	public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication(restUserName, restPassword).build();
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		ResponseEntity<UserModel> user = restTemplate.getForEntity(userServiceUrl +"/v1/user/name/{username}", UserModel.class, username);
		return user.getBody();
		
	}
	
	public List<UserModel> getAllUsers() {
		
		ResponseEntity<List> users = restTemplate.getForEntity(userServiceUrl +"/v1/user/all", List.class);
		return users.getBody();

	}
	
	public UserModel getUser(Long userId) {
		ResponseEntity<UserModel> user = restTemplate.getForEntity(userServiceUrl +"/v1/user/{userId}", UserModel.class, userId);
		return user.getBody();
		
	}

	@Override
	public UserModel saveUser(UserModel userModel) {
		ResponseEntity<UserModel> user = restTemplate.postForEntity(userServiceUrl +"/v1/user/", userModel, UserModel.class);
		return user.getBody();
	}

	@Override
	public void updateUser(String id, UserModel userModel) {
		restTemplate.put(userServiceUrl +"/v1/user/{userId}", userModel);
	}

	@Override
	public void deleteUser(String id) {
		restTemplate.delete(userServiceUrl +"/v1/user/{userId}");
	}

}
