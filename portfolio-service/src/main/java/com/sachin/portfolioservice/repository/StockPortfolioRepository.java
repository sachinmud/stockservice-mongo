package com.sachin.portfolioservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.portfolioservice.domain.StockPortfolio;

public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long>{
	
	Page<StockPortfolio> findByPortfolioId(Long portfolioId, Pageable pageable);

}
