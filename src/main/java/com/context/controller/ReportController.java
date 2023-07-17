package com.context.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return new ResponseEntity<>(service.postProcessedCarts(),HttpStatus.OK);	
	}
	
	@GetMapping(path="/batch/processCarts")
	public ResponseEntity<List<ReportDTO>>getProcesDate(@RequestParam(required=false,value="from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde ,@RequestParam(required=false,value="to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date hasta) {
	     List<ReportDTO> listReportDTO;
	     listReportDTO = (desde == null || hasta==null)?service.getReports():service.getReportsDate(desde, hasta);
	    return new ResponseEntity<>(listReportDTO, HttpStatus.OK);
	}
		
}
