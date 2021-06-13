package com.sachin.userservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.model.UserModel;
import com.sachin.userservice.service.PermissionService;
import com.sachin.userservice.service.RoleService;
import com.sachin.userservice.service.UserService;

@Component
public class ApplicationInitializer implements ApplicationRunner {
	
	@Autowired
	PermissionService service;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		try {
			UserDetails details = null;
			try {
				details = userService.loadUserByUsername("admin");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(details == null) {
				System.out.println("Loading initial data");
				PermissionModel permission1 = new PermissionModel("role:read");
				service.savePermission(permission1);
				PermissionModel permission2 = new PermissionModel("user:delete");
				service.savePermission(permission2);
				PermissionModel permission3 = new PermissionModel("user:write");
				service.savePermission(permission3);
				PermissionModel permission4 = new PermissionModel("user:read");
				service.savePermission(permission4);
				PermissionModel permission5 = new PermissionModel("role:modify");
				service.savePermission(permission5);
				PermissionModel permission6 = new PermissionModel("role:delete");
				service.savePermission(permission6);
				PermissionModel permission7 = new PermissionModel("permission:modify");
				service.savePermission(permission7);
				PermissionModel permission8 = new PermissionModel("permission:delete");
				service.savePermission(permission8);
				
				Set<PermissionModel> adminPermissions = new HashSet<PermissionModel>();
				adminPermissions.add(permission1);
				adminPermissions.add(permission2);
				adminPermissions.add(permission3);
				adminPermissions.add(permission4);
				adminPermissions.add(permission5);
				adminPermissions.add(permission6);
				adminPermissions.add(permission7);
				adminPermissions.add(permission8);
				
				Set<PermissionModel> clientPermissions = new HashSet<PermissionModel>();
				clientPermissions.add(permission1);
				clientPermissions.add(permission4);
				
				RoleModel adminrole = new RoleModel();
				adminrole.setRolename("ROLE_ADMIN");
				adminrole.setPermissions(adminPermissions);
				roleService.saveRole(adminrole);
	
				RoleModel clientrole = new RoleModel();
				clientrole.setRolename("ROLE_CLIENT");
				clientrole.setPermissions(clientPermissions);
				roleService.saveRole(clientrole);
	
				UserModel adminUser = new UserModel();
				adminUser.setUsername("admin");
				adminUser.setFullname("Admin User");
				adminUser.setPassword("password");
				adminUser.setEnabled(true);
				Set<RoleModel> adminroles = new HashSet<RoleModel>();
				adminroles.add(adminrole);
				adminUser.setRoles(adminroles);
				userService.saveUser(adminUser);
	
				UserModel clientUser = new UserModel();
				clientUser.setUsername("user1");
				clientUser.setFullname("Client User");
				clientUser.setPassword("password");
				clientUser.setEnabled(true);
				Set<RoleModel> clientroles = new HashSet<RoleModel>();
				clientroles.add(clientrole);
				clientUser.setRoles(clientroles);
				userService.saveUser(clientUser);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} 		
		
	}

}
