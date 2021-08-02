package com.sachin.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	@PreAuthorize("hasAuthority('user:read')")
	public UserModel getUser(@PathVariable("id") String id) {
		
		return service.getUser(id);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('user:read')")
	public UserDetails getUserByUserName(@PathVariable("name") String name) {
		
		return service.loadUserByUsername(name);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
	@PreAuthorize("hasAuthority('user:read')")
	public List<UserModel> getUsers() {
		
		return service.getAllUsers();
	}
	
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PreAuthorize("hasAuthority('role:read')")
	public List<RoleModel> getRoles(@PathVariable("id") String id) {
		return service.getRolesForUser(id);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	//@PreAuthorize("permitAll()")
	public UserModel save(@RequestBody @Valid UserModel user) {
		
		return service.saveUser(user);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('user:modify')")
	public UserModel update(@RequestBody UserModel user) {
		return service.saveUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('user:modify')")
	public UserModel updateUser(@PathVariable("id") String id, @RequestBody UserModel user) {
		
		return service.saveUser(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('user:delete')")
	public boolean deleteUser(@PathVariable("id") String id) {
		return service.deleteUser(id);
	}	
}
