package com.sachin.portfolioservice.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;

@Document
@Builder
public class StockPortfolio implements Serializable {

	@Id
    private String id;

	@CreatedDate
    private Date createdDate;

	@LastModifiedDate
    private Date updatedDate;

	@DBRef(lazy = true)
	private UserPortfolio portfolio;
	
	
	public UserPortfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(UserPortfolio portfolio) {
		this.portfolio = portfolio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
