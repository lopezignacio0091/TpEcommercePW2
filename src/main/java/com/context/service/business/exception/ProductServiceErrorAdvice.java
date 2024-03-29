package com.context.service.business.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.context.ApiRestHeladeriaApplication;

public class ProductServiceErrorAdvice {

private static final Logger logger = LoggerFactory.getLogger(ApiRestHeladeriaApplication.class);
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    @ExceptionHandler({ProductServiceException.class})
    public ResponseEntity<String> handleDogsServiceException(ProductServiceException e){
        return error(HttpStatus.NOT_FOUND, e);
    }
    private ResponseEntity<String> error(HttpStatus status, Exception e) {
    	logger.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
    
    
}
