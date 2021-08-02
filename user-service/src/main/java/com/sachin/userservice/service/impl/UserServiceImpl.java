package com.sachin.userservice.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Permission;
import com.sachin.userservice.domain.Role;
import com.sachin.userservice.domain.User;
import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.repository.RoleRepository;
import com.sachin.userservice.repository.UserRepository;
import com.sachin.userservice.service.RoleService;
import com.sachin.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	PasswordEncoder passwordEncoder;
	
	UserRepository repository;
	
	RoleService roleService;
	
	@Autowired
	public UserServiceImpl(UserRepository repository, RoleService roleService, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<UserModel> getAllUsers() {
		
		List<User> users = repository.findAll();
		
		Stream<UserModel> userModels = users.stream().map(u -> populateUserModel(new UserModel(), u));
		return userModels.collect(Collectors.toList());
	}
	
	public UserModel getUser(String userId) {
		
		return populateUserModel(new UserModel(), repository.findById(userId).get());
		
	}
	
	public List<RoleModel> getRolesForUser(String userId) {
		return repository.findById(userId).get().getRoles().stream().map(r -> new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel())).collect(Collectors.toList());
	}
	
	public UserModel saveUser(UserModel usermodel) {
		
		User user = new EntityUtils<UserModel, User>().copyProperties(usermodel, User.builder().build());
		user.setPassword(passwordEncoder.encode(usermodel.getPassword()));
		usermodel.getRoles().forEach(r -> {if(r.getId() == null) { r.setId(roleService.getRoleByName(r.getRolename()).getId());}});
		user.setRoles(usermodel.getRoles().stream().map(r -> new EntityUtils<RoleModel, Role>().copyProperties(r, Role.builder().build())).collect(Collectors.toList()));
		user.setEnabled(true);
		user = repository.save(user);
		populateUserModel(usermodel, user);
		return usermodel;
	
	}

	public boolean deleteUser(String userId) {		
		repository.deleteById(userId);
		return true;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user != null) {
			return populateUserModel(new UserModel(), user);
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
	
	private UserModel populateUserModel(UserModel usermodel, User user) {
		List<RoleModel> roles = new ArrayList<>();
		List<PermissionModel> permissions = new ArrayList<>();

		usermodel = new EntityUtils<User, UserModel>().copyProperties(user, usermodel);
		user.getRoles().forEach(r -> {
			RoleModel role = new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel());
			if(r.getPermissions() != null) {
				role.setPermissions(r.getPermissions().stream().map(p -> new EntityUtils<Permission, PermissionModel>().copyProperties(p, new PermissionModel())).collect(Collectors.toList()));
				permissions.addAll(role.getPermissions());
				PermissionModel permission = new PermissionModel();
				permission.setAuthority(role.getRolename());
				permissions.add(permission);
			}
			roles.add(role);
		});
		usermodel.setAuthorities(permissions);
		usermodel.setRoles(roles);
		return usermodel;
	}
	
	public boolean loadInitialData() {
		return true;
	}
}
