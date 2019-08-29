package com.sachin.portfolioservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.portfolioservice.domain.StockItem;


public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	
	Page<StockItem> findByStockPortfolioId(Long stockPortfolioId, Pageable pageable);

}
