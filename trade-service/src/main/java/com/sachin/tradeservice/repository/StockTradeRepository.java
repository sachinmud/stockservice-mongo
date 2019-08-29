package com.sachin.tradeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sachin.tradeservice.domain.StockOrder;

@Repository
public interface StockTradeRepository extends JpaRepository<StockOrder, Long>{

}