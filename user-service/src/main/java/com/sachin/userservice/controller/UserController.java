package com.sachin.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/user")
@Api(value = "User Service", description = "User Details Management")
public class UserController {
	
	@Autowired
	UserService service;

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('user:read')")
	public ResponseEntity<UserModel> getUser(@PathVariable("id") String id) {
		
		return new ResponseEntity<UserModel>(service.getUser(Long.parseLong(id)), HttpStatus.OK);
	}

	@GetMapping(value = "/name/{name}")
	@PreAuthorize("hasAuthority('user:read')")
	public UserDetails getUserByUserName(@PathVariable("name") String name) {
		
		return service.loadUserByUsername(name);
	}

	@GetMapping(value = "/all")
//	@PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
	@PreAuthorize("hasAuthority('user:read')")
	public ResponseEntity<List<UserModel>> getUsers() {
		
		return new ResponseEntity<List<UserModel>>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/roles")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PreAuthorize("hasAuthority('role:read')")
	public ResponseEntity<List<RoleModel>> getRoles(@PathVariable("id") String id) {
		return new ResponseEntity<List<RoleModel>>(service.getRolesForUser(Long.parseLong(id)), HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@PreAuthorize("hasAuthority('user:modify')")
	public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
		user = service.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/v1/user"+String.valueOf(user.getId()));
		return new ResponseEntity<UserModel>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('user:modify')")
	public ResponseEntity<HttpStatus> update(@RequestBody UserModel user, @PathVariable("id") String id) {
		service.saveUser(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('user:delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") String id) {
		service.deleteUser(Long.parseLong(id));
	}	
}
