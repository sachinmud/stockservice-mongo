package com.sachin.unit.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sachin.userservice.domain.Permission;
import com.sachin.userservice.domain.Role;
import com.sachin.userservice.domain.User;
import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.repository.UserRepository;
import com.sachin.userservice.service.UserService;
import com.sachin.userservice.service.impl.RoleServiceImpl;
import com.sachin.userservice.service.impl.UserServiceImpl;

public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Mock
	UserRepository repos;
	
	@BeforeEach
	public void setup( ) {
		MockitoAnnotations.initMocks(this);
		
		Permission permission1 = Permission.builder().id("1").authority("role:read").build();
		Permission permission2 = Permission.builder().id("2").authority("user:delete").build();
		Permission permission3 = Permission.builder().id("3").authority("user:write").build();
		Permission permission4 = Permission.builder().id("4").authority("user:read").build();
		
		List<Permission> adminPermissions = new ArrayList<Permission>();
		adminPermissions.add(permission1);
		adminPermissions.add(permission2);
		adminPermissions.add(permission3);
		adminPermissions.add(permission4);
		
		Role adminrole = Role.builder().id("1").rolename("ROLE_ADMIN").build();
		adminrole.setPermissions(adminPermissions);

		User adminUser = User.builder().username("admin").fullname("Admin User").password("password").enabled(true).build();
		List<Role> adminroles = new ArrayList<Role>();
		adminroles.add(adminrole);
		adminUser.setRoles(adminroles);

		userService = new UserServiceImpl(repos, new RoleServiceImpl(), new BCryptPasswordEncoder(10));
		when(repos.findById("1")).thenReturn(Optional.ofNullable(adminUser));
	}
	
	@Test
	public void testGetUser( ) {
		assertEquals("admin", userService.getUser("1").getUsername());
	}

}
