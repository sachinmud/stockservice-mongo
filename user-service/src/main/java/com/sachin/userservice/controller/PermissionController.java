package com.sachin.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.service.PermissionService;

@RestController
@RequestMapping(value = "v1/permission")
public class PermissionController {
	
	@Autowired
	PermissionService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PreAuthorize("hasAuthority('role:read')")
	public PermissionModel getPermission(@PathVariable("id") String permissionId) {
		return service.getPermission(permissionId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PreAuthorize("hasAuthority('role:read')")
	public List<PermissionModel> getAllPermissions() {
		return service.getAllPermissions();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('permission:modify')")
	public PermissionModel savePermission(@RequestBody PermissionModel permission) {
		return service.savePermission(permission);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('permission:delete')")
	public boolean deletePermission(@PathVariable("id") String permissionId) {
		return service.deletePermission(permissionId);
	}	

}
