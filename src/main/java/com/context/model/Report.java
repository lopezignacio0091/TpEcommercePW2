package com.context.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Report {
	
	public int getTotalCartsProcessed() {
		return totalCartsProcessed;
	}
	public void cart(int totalCartsProcessed) {
		this.totalCartsProcessed = totalCartsProcessed;
	}
	public int getTotalCartsFailed() {
		return totalCartsFailed;
	}
	public void setTotalCartsFailed(int totalCartsFailed) {
		this.totalCartsFailed = totalCartsFailed;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public Set<Product> getWithoutStockProducts() {
		return withoutStockProducts;
	}
	public void setWithoutStockProducts(Set<Product> products) {
		this.withoutStockProducts = products;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private @Id @GeneratedValue Long id;
	private int totalCartsProcessed=0;
	private int totalCartsFailed = 0 ;
	private BigDecimal profit = BigDecimal.ZERO;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Product> withoutStockProducts;
	private Date ProcessedDateTime =  new Date (System.currentTimeMillis());
	
	public void setProcessedDateTime(Date now) {
		// TODO Auto-generated method stub
		
	}
	public Date getProcessedDateTime() {
		return ProcessedDateTime;
	}
	public void setTotalCartsProcessed(int i) {
		this.totalCartsProcessed = i;
		
	}
}


