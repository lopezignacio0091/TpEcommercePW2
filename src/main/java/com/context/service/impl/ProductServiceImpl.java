package com.context.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import com.context.model.Product;
import com.context.model.dto.ProductDTO;
import com.context.repository.ProductRepository;
import com.context.service.ProductService;
import com.context.service.business.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl  implements ProductService{

private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	
	public List<ProductDTO> getAll() {

		List<Product> product = productRepo.findAll();
		List<ProductDTO> dtos = new ArrayList<>(product.size());
		
		for (int i = 0; i < product.size(); i++) {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(product.get(i), productDTO);
			dtos.add(productDTO);
		}
		
		return dtos;
	}
	
	
	
	@Override
	public ProductDTO getById(Long id) {
		
		Optional<Product> product = productRepo.findById(id);
		ProductDTO dto = new ProductDTO();
		
		if (product.isPresent()) {
			BeanUtils.copyProperties(product.get(), dto);
		} else {
			throw new ProductNotFoundException("Product " + id + " not found");
		}
		
		return dto;
	}

	
	@Override
	public Long post(ProductDTO productDTO) {

		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		product = productRepo.save(product);
		
		return product.getId();
	}
	
	
	@Override
	public Long put(ProductDTO productDTO) {
    
		Optional<Product> product = productRepo.findById(productDTO.getId());
		Product productNew= new Product();
		
		if (product.isPresent()) {
			BeanUtils.copyProperties(productDTO, productNew);
			productRepo.save(productNew);
			} 
		else {
			throw new ProductNotFoundException("Product" + productDTO.getId()+" "+" Not found");
		}	
		return productDTO.getId();
	}
	
	
	@Override
	public void deleteById(Long id) {	
		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
		productRepo.deleteById(id);
	}else {
		throw new ProductNotFoundException("Product" +id+" "+" Not found");
		}
	
	}


	@Override
	public void msj() {
		System.out.println("hola");
		
	}
	
	
}
