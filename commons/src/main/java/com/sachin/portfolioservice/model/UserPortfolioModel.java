package com.sachin.portfolioservice.model;

import java.util.Date;

public class UserPortfolioModel {
	
	private Long id;

	private String name;
	
	private StockPortfolioModel stockPortfolio = new StockPortfolioModel();
	
    private Date createdDate;

    private Date updatedDate;
    
    private String signature;
    
    private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StockPortfolioModel getStockPortfolio() {
		return stockPortfolio;
	}

	public void setStockPortfolio(StockPortfolioModel stockPortfolio) {
		this.stockPortfolio = stockPortfolio;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
