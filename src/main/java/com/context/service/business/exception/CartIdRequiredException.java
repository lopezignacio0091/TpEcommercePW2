package com.context.service.business.exception;

import org.springframework.http.HttpStatus;

public class CartIdRequiredException extends BaseECommersException{
	private static final long serialVersionUID = 5025516969739672390L;

	public CartIdRequiredException() {
		super(HttpStatus.BAD_REQUEST, "CART_ID_REQUIRED", "Cart id is required");
	}
}


