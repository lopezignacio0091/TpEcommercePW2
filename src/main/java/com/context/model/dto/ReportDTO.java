package com.context.model.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.context.model.Product;

public class ReportDTO {

	private Long id ;
	private LocalDate processedDateTime ;
	private int profit; 
	private int totalCartsFailed ;
	private int totalCartProcessed;
	private Set<Product> withoutStockProducts;
    
    
    
	public Set<Product> getWithoutStockProducts() {
		return withoutStockProducts;
	}
	public void setWithoutStockProducts(Set<Product> withoutStockProducts) {
		this.withoutStockProducts = withoutStockProducts;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getProcessedDateTime() {
		return processedDateTime;
	}
	public void setProcessedDateTime(LocalDate processedDateTime) {
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
	
	 
    
}
