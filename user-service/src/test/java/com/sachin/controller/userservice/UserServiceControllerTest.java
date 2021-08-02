package com.sachin.controller.userservice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sachin.userservice.controller.UserController;
import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceControllerTest {
	
	@Mock
	UserService service;
	
	@InjectMocks
	UserController controller;
	
	MockMvc mockMvc;
	
	UserModel adminUser;
	
	@BeforeEach
	public void setup() {
		PermissionModel permission1 = new PermissionModel();
		permission1.setAuthority("role:read");
		PermissionModel permission2 = new PermissionModel();
		permission2.setAuthority("user:delete");
		PermissionModel permission3 = new PermissionModel();
		permission3.setAuthority("user:write");
		PermissionModel permission4 = new PermissionModel();
		permission4.setAuthority("user:read");
		
		List<PermissionModel> adminPermissions = new ArrayList<PermissionModel>();
		adminPermissions.add(permission1);
		adminPermissions.add(permission2);
		adminPermissions.add(permission3);
		adminPermissions.add(permission4);
		
		
		RoleModel adminrole = new RoleModel();
		adminrole.setRolename("ROLE_ADMIN");
		adminrole.setPermissions(adminPermissions);

		adminUser = new UserModel();
		adminUser.setUsername("admin");
		adminUser.setFullname("Admin User");
		adminUser.setPassword("password");
		adminUser.setEnabled(true);
		List<RoleModel> adminroles = new ArrayList<RoleModel>();
		adminroles.add(adminrole);
		adminUser.setRoles(adminroles);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void getUserByUserNameTest() throws Exception {
		
		when(service.loadUserByUsername("admin")).thenReturn(adminUser);
		
		mockMvc.perform(get("/v1/user/name/{name}", "admin")).andExpect(status().is(200));
	}
}
