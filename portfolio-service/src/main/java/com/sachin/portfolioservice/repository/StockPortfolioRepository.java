package com.sachin.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.StockPortfolio;

@Repository
public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long>{
	
	@Query(value = "select * from stockportfolio sp where sp.portfolioid = :portfolioId", nativeQuery = true)
	StockPortfolio findByPortfolioId(@Param("portfolioId") Long portfolioId);

}
