package com.sachin.stockservice.delegate.impl;

import com.sachin.stockservice.api.StockQuoteApiDelegate;
import com.sachin.stockservice.model.StockEvent;
import com.sachin.stockservice.model.StockRequest;
import com.sachin.stockservice.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockQuoteApiDelegateImpl implements StockQuoteApiDelegate {

    @Autowired
    StockService service;

    Logger logger = Logger.getLogger(StockQuoteApiDelegateImpl.class);

    @Override
    public ResponseEntity<StockEvent> stockQuotePost(StockRequest stockRequest) {
        ResponseEntity<StockEvent> response;
        try {
            StockEvent event = service.postStockEvent(stockRequest);
            response = new ResponseEntity<>(event, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            logger.error(e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
