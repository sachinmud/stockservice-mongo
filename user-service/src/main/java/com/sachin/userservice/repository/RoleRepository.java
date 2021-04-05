package com.sachin.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.userservice.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

}
