package com.sachin.userservice.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;;

@Document
public class Permission implements GrantedAuthority {
	
	@Id
	private String id;
	
	private String authority;
	
	public Permission() {
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Permission(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof Permission) {
			return authority.equals(((Permission) obj).authority);
		}

		return false;
	}

	@Override
	public String toString() {
		return this.authority;
	}
	

}
