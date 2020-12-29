package com.context.model.dto;

import com.context.model.Product;

public class ReportDTO {

	private Long id ;
	private String processedDateTime ;
	private int profit; 
	private int totalCartsFailed ;
	private int totalCartProcessed;
    Product withoutStockProducts;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcessedDateTime() {
		return processedDateTime;
	}
	public void setProcessedDateTime(String processedDateTime) {
		this.processedDateTime = processedDateTime;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public int getTotalCartsFailed() {
		return totalCartsFailed;
	}
	public void setTotalCartsFailed(int totalCartsFailed) {
		this.totalCartsFailed = totalCartsFailed;
	}
	public int getTotalCartProcessed() {
		return totalCartProcessed;
	}
	public void setTotalCartProcessed(int totalCartProcessed) {
		this.totalCartProcessed = totalCartProcessed;
	}
	public Product getWithoutStockProducts() {
		return withoutStockProducts;
	}
	public void setWithoutStockProducts(Product withoutStockProducts) {
		this.withoutStockProducts = withoutStockProducts;
	}
	
    
    
}
