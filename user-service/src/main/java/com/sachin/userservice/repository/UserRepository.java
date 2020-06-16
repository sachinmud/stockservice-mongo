package com.sachin.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.userservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
