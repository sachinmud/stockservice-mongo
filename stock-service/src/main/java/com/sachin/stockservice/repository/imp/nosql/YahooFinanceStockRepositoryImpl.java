package com.sachin.stockservice.repository.imp.nosql;

import org.springframework.stereotype.Repository;

import com.sachin.stockservice.domain.Stock;
import com.sachin.stockservice.repository.StockRepository;
import com.sachin.stockservice.util.YahooFinanceUtil;

@Repository("yahoofinance")
public class YahooFinanceStockRepositoryImpl implements StockRepository {

	@Override
	public Stock selectStock(String code) {
		return YahooFinanceUtil.getStockDetails(code);
	}

}
