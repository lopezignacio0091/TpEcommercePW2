package com.context.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.context.utils.Status;

@Entity
public class Cart implements  Comparable<Cart>{

	private @Id @GeneratedValue Long id;
	private String fullName; 
	private String email ;
	private LocalDate creationDate ;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<CartProduct> products = new HashSet<CartProduct>();
	private BigDecimal total ;
	private Status status; 
	private Date checkDate ;

	
	
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Cart() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	 
	 @OneToMany(mappedBy = "cart")
	public Set<CartProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<CartProduct> products) {
		this.products = products;
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

	@Override
	public int compareTo(Cart arg0) {
		 if (getCreationDate() == null || arg0.getCreationDate() == null) {
		      return 0;
		    }
		    return getCreationDate().compareTo(arg0.getCreationDate());
		  }
	}


