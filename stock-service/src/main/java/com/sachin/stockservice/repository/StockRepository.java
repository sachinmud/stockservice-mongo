package com.sachin.stockservice.repository;

import com.sachin.stockservice.domain.Stock;

public interface StockRepository {
	
	public Stock selectStock(String code) throws Exception;

}
