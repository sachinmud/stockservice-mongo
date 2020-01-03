package com.sachin.portfolioservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockPortfolioModel {
	
	private long id;
	
	private List<StockItemModel> stockItems = new ArrayList<StockItemModel>();
	
    private Date createdDate;

    private Date updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<StockItemModel> getStockItems() {
		return stockItems;
	}

	public void setStockItems(List<StockItemModel> stockItems) {
		this.stockItems = stockItems;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
    
}
