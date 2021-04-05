package com.sachin.userservice.model;

import java.util.HashSet;
import java.util.Set;

public class RoleModel {
	
	private String id;
	
	private String rolename;
	
	private Set<PermissionModel> permissions;
	
	public void setPermissions(Set<PermissionModel> permissions) {
		this.permissions = permissions;
	}

	public Set<PermissionModel> getPermissions() {
		return permissions;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
