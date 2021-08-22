package com.sachin.portfolioservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.UserPortfolio;

@Repository
public interface PortfolioRepository extends MongoRepository<UserPortfolio, String>{
	
	@Query(value = "{'userId':?0}")
	public UserPortfolio findByUserId(@Param("userId") String userId);

}
