package com.sachin.tradeservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sachin.tradeservice.domain.StockOrder;

@Repository
public interface StockTradeRepository extends MongoRepository<StockOrder, String>{

}