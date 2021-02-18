package com.context.service.business.exception;

import org.springframework.http.HttpStatus;

public class ProductStockRequiredException extends BaseECommersException {
	private static final long serialVersionUID = -7012327687863428836L;

	public ProductStockRequiredException() {
		super (HttpStatus.BAD_REQUEST, "PRODUCT_STOCK_REQUIRED", "Product stock is required");
	}

}
