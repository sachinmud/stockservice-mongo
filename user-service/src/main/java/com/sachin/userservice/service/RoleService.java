package com.sachin.userservice.service;

import java.util.List;
import java.util.Set;

import com.sachin.userservice.model.RoleModel;

public interface RoleService {
	
	public RoleModel getRole(Long roleId);
	
	public Set<RoleModel> getAllRoles();
	
	public RoleModel saveRole(RoleModel role);
	
	public boolean deleteRole(Long roleId);

}
