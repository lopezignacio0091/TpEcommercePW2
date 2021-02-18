package com.context.service.business.exception;

import org.springframework.http.HttpStatus;

public class ProductNotPresentException extends BaseECommersException {
	private static final long serialVersionUID = -4643546219001889379L;

	public ProductNotPresentException() {
		super (HttpStatus.NOT_FOUND, "PRODUCT_NOT_PRESENT", "Invalid product") ;
	}

	
}
