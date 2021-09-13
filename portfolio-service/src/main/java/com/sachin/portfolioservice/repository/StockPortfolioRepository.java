package com.sachin.portfolioservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sachin.portfolioservice.domain.StockPortfolio;

@Repository
public interface StockPortfolioRepository extends MongoRepository<StockPortfolio, String>{
	
	@Query(value = "{'portfolio.$id':?0}")
	StockPortfolio findByPortfolioId(@Param("portfolioId") ObjectId portfolioId);

}
