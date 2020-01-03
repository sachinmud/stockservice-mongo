package com.sachin.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Role;
import com.sachin.userservice.model.RoleModel;
import com.sachin.userservice.repository.RoleRepository;
import com.sachin.userservice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository repository;

	@Override
	public RoleModel getRole(Long roleId) {
		return new EntityUtils<Role, RoleModel>().copyProperties(repository.getOne(roleId), new RoleModel());
	}

	@Override
	public List<RoleModel> getAllRoles() {
		return repository.findAll().stream().map(r -> new EntityUtils<Role, RoleModel>().copyProperties(r, new RoleModel())).collect(Collectors.toList());
	}

	@Override
	public RoleModel saveRole(RoleModel role) {
		return new EntityUtils<Role, RoleModel>().copyProperties(repository.save(new EntityUtils<RoleModel, Role>().copyProperties(role, new Role())), role);
	}

	@Override
	public boolean deleteRole(Long roleId) {
		repository.deleteById(roleId);
		return true;
	}

}
