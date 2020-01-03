package com.sachin.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.UserPortfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<UserPortfolio, Long>{
	
	@Query(value = "select * from portfolio where userid = :userId", nativeQuery = true)
	public UserPortfolio findByUserId(@Param("userId") Long userId);

}
