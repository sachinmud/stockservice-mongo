package com.sachin.userservice.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Permission;
import com.sachin.userservice.domain.Role;
import com.sachin.userservice.model.PermissionModel;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.repository.RoleRepository;
import com.sachin.userservice.service.PermissionService;
import com.sachin.userservice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository repository;
	
	@Autowired
	PermissionService permissionService;

	@Override
	public RoleModel getRole(String roleId) {
		return new EntityUtils<Role, RoleModel>().copyProperties(repository.findById(roleId).get(), new RoleModel());
	}

	@Override
	public Set<RoleModel> getAllRoles() {
		Set<RoleModel> roles = new HashSet();
		repository.findAll().forEach(r -> {
			RoleModel role = new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel());
			role.setPermissions(r.getPermissions().stream().map(p -> new EntityUtils<Permission, PermissionModel>().copyProperties(p, new PermissionModel())).collect(Collectors.toSet()));
			roles.add(role);
		});
		return roles;
	}

	@Override
	public RoleModel saveRole(RoleModel roleVO) {
		Role role = new EntityUtils<RoleModel, Role>().copyProperties(roleVO, new Role());
		role.setPermissions(roleVO.getPermissions().stream().map(p -> new EntityUtils<PermissionModel, Permission>().copyProperties(p, new Permission())).collect(Collectors.toSet()));
		role = repository.save(role);
		return new EntityUtils<Role, RoleModel>().copyProperties(role, roleVO);
	}

	@Override
	public boolean deleteRole(String roleId) {
		repository.deleteById(roleId);
		return true;
	}

}
