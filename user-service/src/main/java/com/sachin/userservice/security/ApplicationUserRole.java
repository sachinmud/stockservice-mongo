package com.sachin.userservice.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
import static com.sachin.userservice.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	
	ADMIN(Sets.newHashSet(USER_READ, USER_MODIFY, USER_DELETE
			,ROLE_READ, ROLE_MODIFY, ROLE_DELETE)),
	CLIENT(Sets.newHashSet(USER_READ, ROLE_READ));
	
	private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
    
    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
    
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }    
}
