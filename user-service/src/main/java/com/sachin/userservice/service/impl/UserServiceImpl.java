package com.sachin.userservice.service.impl;

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
import com.sachin.userservice.repository.UserRepository;
import com.sachin.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository repository;
	
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
		
		User user = new EntityUtils<UserModel, User>().copyProperties(usermodel, new User());
		user.setPassword(passwordEncoder.encode(usermodel.getPassword()));
		user.setRoles(usermodel.getRoles().stream().map(r -> new EntityUtils<RoleModel, Role>().copyProperties(r, new Role())).collect(Collectors.toSet()));		
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
		Set<RoleModel> roles = new HashSet<>();
		Set<PermissionModel> permissions = new HashSet<>();

		usermodel = new EntityUtils<User, UserModel>().copyProperties(user, usermodel);
		user.getRoles().forEach(r -> {
			RoleModel role = new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel());
			role.setPermissions(r.getPermissions().stream().map(p -> new EntityUtils<Permission, PermissionModel>().copyProperties(p, new PermissionModel())).collect(Collectors.toSet()));
			roles.add(role);
			permissions.addAll(role.getPermissions());
			permissions.add(new PermissionModel(role.getRolename()));
		});
		usermodel.setAuthorities(permissions);
		usermodel.setRoles(roles);
		return usermodel;
	}
}
