package com.context.service.business.exception;


import org.springframework.http.HttpStatus;

public class ProductIdNotRequiredException extends RuntimeException {
	private static final long serialVersionUID = 2847576973986489336L;

	public ProductIdNotRequiredException(String message) {
		super(message);
	}

}
