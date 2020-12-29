package com.context.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Report {

	private  @Id @GeneratedValue Long id ;
	private String processedDateTime ;
	private int profit; 
	private int totalCartsFailed ;
	private int totalCartProcessed;
	
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
	
	
}


