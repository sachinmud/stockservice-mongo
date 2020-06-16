package com.sachin.userservice.security;

public enum ApplicationUserPermission {
	
	USER_READ("user:read"),
	USER_MODIFY("user:modify"),
	USER_DELETE("user:delete"),
	ROLE_READ("role:read"),
	ROLE_MODIFY("role:modify"),
	ROLE_DELETE("role:delete"),
	PERMISSION_MODIFY("permission:modify"),
	PERMISSION_DELETE("permission:delete");

	private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
