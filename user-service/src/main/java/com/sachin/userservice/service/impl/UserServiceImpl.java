package com.sachin.userservice.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Role;
import com.sachin.userservice.domain.User;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.repository.UserRepository;
import com.sachin.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<UserModel> getAllUsers() {
		
		List<User> users = repository.findAll();
		
		Stream<UserModel> userModels = users.stream().map(u -> new EntityUtils<User, UserModel>().copyProperties(u, new UserModel()));
		
		return userModels.collect(Collectors.toList());
	}
	
	public UserModel getUser(Long userId) {
		
		return new EntityUtils<User, UserModel>().copyProperties(repository.getOne(userId), new UserModel());
		
	}
	
	public List<RoleModel> getRolesForUser(Long userId) {
		return repository.getOne(userId).getRoles().stream().map(r -> new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel())).collect(Collectors.toList());
	}
	
	public UserModel saveUser(UserModel usermodel) {
		
		User user = new EntityUtils<UserModel, User>().copyProperties(usermodel, new User());
		user.setRoles(usermodel.getRoles().stream().map(r -> new EntityUtils<RoleModel, Role>().copyProperties(r, new Role())).collect(Collectors.toSet()));		
		user = repository.save(user);
		return new EntityUtils<User, UserModel>().copyProperties(user, usermodel);
	
	}

	public boolean deleteUser(Long userId) {		
		repository.deleteById(userId);
		return true;
	}
}
