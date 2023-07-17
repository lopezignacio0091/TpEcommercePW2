package com.context.service.business.exception;

public class CartInsufficientException  extends RuntimeException {

	private static final long serialVersionUID = 5396846643347699941L;
	
		
	public CartInsufficientException(String message) {
	       super(message);
		}
}
