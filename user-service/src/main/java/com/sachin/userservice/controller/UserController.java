package com.sachin.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("v1/user")
@Api(value = "User Service", description = "User Details Management")
public class UserController {
	
	@Autowired
	UserService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserModel getUser(@PathVariable("id") String id) {
		
		return service.getUser(Long.parseLong(id));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserModel> getUsers() {
		
		return service.getAllUsers();
	}
	
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public List<RoleModel> getRoles(@PathVariable("id") String id) {
		return service.getRolesForUser(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public UserModel save(@RequestBody UserModel user) {
		
		return service.saveUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserModel updateUser(@PathVariable("id") String id, @RequestBody UserModel user) {
		
		return service.saveUser(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable("id") String id) {
		return service.deleteUser(Long.parseLong(id));
	}	
}
