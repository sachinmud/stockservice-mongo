package com.sachin.portfolioservice.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.StockItem;

@Repository
public interface StockItemRepository extends MongoRepository<StockItem, String> {
	
	@Query(value = "{'stockPortfolio.$id':?0}")
	List<StockItem> findByStockPortfolioId(@Param("stockPortfolioId") ObjectId stockPortfolioId);

}
