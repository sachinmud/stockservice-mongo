package com.sachin.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sachin.userservice.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
