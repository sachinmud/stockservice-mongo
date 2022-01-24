package com.sachin.stockservice.analyser.executor.impl;

import com.sachin.stockservice.analyser.executor.AnalyticsExecutor;
import com.sachin.stockservice.analyser.executor.module.AnalyticsExecutorContext;

public class MACDExecutorImpl implements AnalyticsExecutor {

    @Override
    public void execute(AnalyticsExecutorContext context) {

        String date = context.getAnalysisDate();
        String stockId = context.getStockId();



    }
}
