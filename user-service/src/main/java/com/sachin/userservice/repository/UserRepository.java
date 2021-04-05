package com.sachin.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.userservice.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);

}
