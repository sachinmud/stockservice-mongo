package com.sachin.stockservice.repository.imp.nosql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sachin.stockservice.domain.Stock;
import com.sachin.stockservice.repository.StockRepository;
import com.sachin.stockservice.util.YahooAPIUtil;

@Repository
public class StockNoSQLRepositoryImpl implements StockRepository {
	
	Logger logger = Logger.getLogger(StockNoSQLRepositoryImpl.class);

	@Override
	public Stock selectStock(String code) throws Exception {

		long startDate = Instant.now().minus(5, ChronoUnit.DAYS).getEpochSecond();
		long endDate = Instant.now().getEpochSecond();

		YahooAPIUtil c = new YahooAPIUtil();
		Stock stock = null;
		
		String crumb = c.getCrumb(code);
		if (crumb != null && !crumb.isEmpty()) {
			logger.debug(String.format("Downloading data to %s", code));
			
			File file = c.downloadData(code, startDate, endDate, crumb);
			
			try(BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				String [] attributes = null;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				while((line = br.readLine()) != null) {
					attributes = line.split(",");
				}
				if(attributes != null && attributes.length > 0) {
					stock = new Stock();
					stock.setCode(code);
					stock.setValueDate(df.parse(attributes[0]));
					stock.setOpen(new BigDecimal(attributes[1]));
					stock.setHigh(new BigDecimal(attributes[2]));
					stock.setLow(new BigDecimal(attributes[3]));
					stock.setClose(new BigDecimal(attributes[4]));
					stock.setAdjClose(new BigDecimal(attributes[5]));
					stock.setVolume(new Long(attributes[6]));
				}
				
			} catch(Exception e) {
				throw e;
			}
		}
		return stock;
	}


}
