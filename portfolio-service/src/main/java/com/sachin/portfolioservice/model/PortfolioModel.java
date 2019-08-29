package com.sachin.portfolioservice.model;

import java.util.Date;
import java.util.List;

import com.sachin.portfolioservice.domain.StockPortfolio;

public class PortfolioModel {
	
	private Long id;

	private String name;
	
	private List<StockPortfolioModel> stockPortfolio;
	
    private Date createdDate;

    private Date updatedDate;

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

	public List<StockPortfolioModel> getStockPortfolio() {
		return stockPortfolio;
	}

	public void setStockPortfolio(List<StockPortfolioModel> stockPortfolio) {
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

}
