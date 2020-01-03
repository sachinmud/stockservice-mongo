package com.sachin.userservice.service;

import java.util.List;

import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;

public interface UserService {
	
	public UserModel saveUser(UserModel usermodel);
	
	public List<UserModel> getAllUsers();
	
	public UserModel getUser(Long userId);
	
	public List<RoleModel> getRolesForUser(Long userId);
	
	public boolean deleteUser(Long userId);

}
