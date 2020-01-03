package com.sachin.userservice.service;

import java.util.List;

import com.sachin.userservice.model.RoleModel;

public interface RoleService {
	
	public RoleModel getRole(Long roleId);
	
	public List<RoleModel> getAllRoles();
	
	public RoleModel saveRole(RoleModel role);
	
	public boolean deleteRole(Long roleId);

}
