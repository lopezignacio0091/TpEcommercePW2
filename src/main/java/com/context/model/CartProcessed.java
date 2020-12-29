package com.context.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CartProcessed {

	private @Id @GeneratedValue Long id ; 
	private int totalCartsProcessed ; 
	private int totalCartsFailed; 
	private BigDecimal profit;
	@OneToMany(fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Product> productStock;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTotalCartsProcessed() {
		return totalCartsProcessed;
	}
	public void setTotalCartsProcessed(int totalCartsProcessed) {
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
	public Set<Product> getProductStock() {
		return productStock;
	}
	public void setProductStock(Set<Product> products) {
		this.productStock = products;
	}
	
	
	
}
