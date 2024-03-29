package com.context.model.dto;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.context.model.CartProduct;
import com.context.utils.Status;

public class CartDTO {

	private Long id;
	private String fullName; 
	private String email ;
	private Date creationDate = new Date (System.currentTimeMillis());
	private Set<CartProduct> products = new HashSet<CartProduct>();
	private BigDecimal total =new BigDecimal("0");
	private Status status = Status.NEW;
	private Date checkDate ;
	
	
	public Date getCheckDate() {
		return checkDate;
	}



	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}



	public CartDTO() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getfullName() {
		return fullName;
	}



	public void setfullName(String fullName) {
		this.fullName = fullName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Set<CartProduct> getProducts() {
		return products;
	}



	public void setProducts(Set<CartProduct> products) {
		this.products = products;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
	
}
