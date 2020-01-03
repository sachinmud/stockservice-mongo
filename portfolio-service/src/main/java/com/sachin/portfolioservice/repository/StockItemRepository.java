package com.sachin.portfolioservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	
	@Query(value = "select * from stockitem where stockportfolioid = :stockPortfolioId", nativeQuery = true)
	List<StockItem> findByStockPortfolioId(@Param("stockPortfolioId") Long stockPortfolioId);

}
