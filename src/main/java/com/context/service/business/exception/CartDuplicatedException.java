package com.context.service.business.exception;

public class CartDuplicatedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422641255422621133L;

	public CartDuplicatedException(String message) {
       super(message);
	}

}
