package com.sachin.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

}
