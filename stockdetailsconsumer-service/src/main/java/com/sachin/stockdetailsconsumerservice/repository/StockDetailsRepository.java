package com.sachin.stockdetailsconsumerservice.repository;

import com.sachin.stock.StockDetailsEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailsRepository extends MongoRepository<StockDetailsEvent, String> {
}
