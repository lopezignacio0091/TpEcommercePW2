package com.context.service.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductServiceException extends RuntimeException {

	private static final long serialVersionUID = -2078724096703508304L;
	
	public ProductServiceException(String message) {
        super(message);
    }
}
