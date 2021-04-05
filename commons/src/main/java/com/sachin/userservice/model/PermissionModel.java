package com.sachin.userservice.model;

import org.springframework.security.core.GrantedAuthority;

public class PermissionModel implements GrantedAuthority {
	
	private String id;
	
	private String authority;
	
	public PermissionModel() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PermissionModel(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
