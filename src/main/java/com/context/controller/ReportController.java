package com.context.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.context.model.dto.ReportDTO;

import com.context.service.ReportService;

@RestController
@Validated
public class ReportController {

	private final ReportService service;
	
	public ReportController(ReportService service) {
		super();
		this.service = service;
	}
	
	@PostMapping(path ="/batch/processCarts")
	public ResponseEntity<Object> batchProcess(){
		
		service.getProcessedCarts();
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
}
