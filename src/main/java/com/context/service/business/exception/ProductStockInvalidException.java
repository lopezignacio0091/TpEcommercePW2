package com.context.service.business.exception;

import org.springframework.http.HttpStatus;

public class ProductStockInvalidException extends BaseECommersException{
	private static final long serialVersionUID = 4167691139384403219L;

	public ProductStockInvalidException() {
		super (HttpStatus.BAD_REQUEST, "PRODUCT_STOCK_INVALID", "Product stock must be greater than 0");
	}

}
