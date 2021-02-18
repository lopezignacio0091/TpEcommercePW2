package com.context.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.context.service.ProductService;
import com.context.model.dto.ProductDTO;


@RestController
@Validated
public class ProductController {
	
private final ProductService service;
	
	public ProductController(ProductService service) {
		super();
		this.service = service;
	}

	
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductDTO> getProductId(@PathVariable long id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(path = "/products")
	public ResponseEntity<Object> postProduct(@Valid @RequestBody ProductDTO productDTO) {
		Long id = service.post(productDTO);
		return new ResponseEntity<>("Product successfully created. Id: " + id, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Object> putProduct(@PathVariable  Optional<Long> id, @Valid @RequestBody ProductDTO productDTO) {
		service.put(id,productDTO);
		return new ResponseEntity<>("Product successfully updated. Id: " + id, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable  Optional<Long> id) {
		service.deleteById(id);
		return new ResponseEntity<>("Product deleted successfully: " + id, HttpStatus.OK);
	}
	
	

}
