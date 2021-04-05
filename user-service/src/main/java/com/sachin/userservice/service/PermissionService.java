package com.sachin.userservice.service;

import java.util.List;

import com.sachin.userservice.model.PermissionModel;


public interface PermissionService {
	
	public PermissionModel getPermission(String permissionId);
	
	public List<PermissionModel> getAllPermissions();
	
	public PermissionModel savePermission(PermissionModel permission);
	
	public boolean deletePermission(String permissionId);

}
