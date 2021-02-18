package com.context.service.business.exception;

import org.springframework.http.HttpStatus;

public class CartEmailRequiredException extends BaseECommersException {

	private static final long serialVersionUID = -7074315547059545209L;

	public CartEmailRequiredException() {
		super (HttpStatus.BAD_REQUEST, "CART_EMAIL_REQUIRED", "Cart email is required");
	}

}
