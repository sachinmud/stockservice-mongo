package com.sachin.userservice.model;

import java.util.HashSet;
import java.util.Set;

public class RoleModel {
	
	private long id;
	
	private String rolename;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
