package com.sachin.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Permission;
import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.repository.PermissionRepository;
import com.sachin.userservice.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionRepository repository;
	
	@Override
	public PermissionModel getPermission(String permissionId) {
		
		return new EntityUtils<Permission, PermissionModel>().copyProperties(repository.findById(permissionId).get(), new PermissionModel());
	}
	
	public List<PermissionModel> getAllPermissions() {
		return repository.findAll().stream().map(r -> new EntityUtils<Permission, PermissionModel>().copyProperties(r, new PermissionModel())).collect(Collectors.toList());
	}
	
	public PermissionModel savePermission(PermissionModel permission) {
		return new EntityUtils<Permission, PermissionModel>().copyProperties(repository.save(new EntityUtils<PermissionModel, Permission>().copyProperties(permission, new Permission())), permission);
	}
	
	public boolean deletePermission(String permissionId) {
		repository.deleteById(permissionId);
		return true;
	}
	
}
