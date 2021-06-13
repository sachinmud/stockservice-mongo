package com.sachin.userservice.service;

import java.util.List;
import java.util.Set;

import com.sachin.userservice.model.RoleModel;

public interface RoleService {
	
	public RoleModel getRole(String roleId);
	
	public RoleModel getRoleByName(String roleName);

	public Set<RoleModel> getAllRoles();
	
	public RoleModel saveRole(RoleModel role);
	
	public boolean deleteRole(String roleId);
	
	public boolean loadInitialData();

}
