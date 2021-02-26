package com.context.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.context.model.Product;

public class ReportDTO {

	private Long id ;
	private Date processedDateTime ;
	private BigDecimal profit; 
	private int totalCartsFailed;
	private int totalCartsProcessed;
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
	public Date getProcessedDateTime() {
		return processedDateTime;
	}
	public void setProcessedDateTime(Date processedDateTime) {
		this.processedDateTime = processedDateTime;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public int getTotalCartsFailed() {
		return totalCartsFailed;
	}
	public void setTotalCartsFailed(int totalCartsFailed) {
		this.totalCartsFailed = totalCartsFailed;
	}
	public int getTotalCartsProcessed() {
		return totalCartsProcessed;
	}
	public void setTotalCartsProcessed(int totalCartProcessed) {
		this.totalCartsProcessed = totalCartProcessed;
	}
	public ReportDTO() {
		super();
		
	}
	
	 
    
}
