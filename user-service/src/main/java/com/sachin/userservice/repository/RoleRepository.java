package com.sachin.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.userservice.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
