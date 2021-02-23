package com.context.service.business.exception;


public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4961625479507744127L;
	

	public ProductNotFoundException() {	
        super("Invalid product");
    }


	

	
}
