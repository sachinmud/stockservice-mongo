package com.sachin.portfolioservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.portfolioservice.domain.StockItem;
import com.sachin.portfolioservice.repository.StockItemRepository;
import com.sachin.portfolioservice.service.StockItemService;

@Service
public class StockItemServiceImpl implements StockItemService {
	
	@Autowired
	StockItemRepository stockItemRepository;

	public StockItem saveStockItem(StockItem stockItem) {
		return stockItemRepository.save(stockItem);
	}
}
