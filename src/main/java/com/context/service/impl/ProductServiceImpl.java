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
import com.context.service.business.exception.*;


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
		if (!product.isPresent()) {
			throw new ProductNotFoundException("Product " + id + " not found");	
		}
		BeanUtils.copyProperties(product.get(), dto);
		
		return dto;
	}

	
	@Override
	public Long post(ProductDTO productDTO) {
		
		if (productDTO.getDescription() == null) {
			throw new ProductDescriptionRequiredException();
		}
		if (productDTO.getStock() == null) {
			throw new ProductStockRequiredException();
		}
		if (productDTO.getStock() < 0) {
			throw new ProductStockInvalidException();
		}
		if (productDTO.getUnitPrice() == null) {
			throw new ProductUnitPriceRequiredException();
		}
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		product = productRepo.save(product);
		return product.getId();
	}
	
	
	@Override
	public Long put(Optional<Long> id, ProductDTO productDTO) {
    
		if (!id.isPresent()) {
			throw new ProductIdRequiredException();	
		}
		productDTO.setId(id.get());
		Optional<Product> _product  = this.productRepo.findById(productDTO.getId());
		if (!_product.isPresent()) {
			throw new ProductNotFoundException("Product " + id + " not found");
		}
		if (productDTO.getId() == null) {
			throw new ProductIdRequiredException();
		}
		if (productDTO.getDescription() == null) {
			throw new ProductDescriptionRequiredException();
		}
		if (productDTO.getStock() == null) {
			throw new ProductStockRequiredException();
		}
		if (productDTO.getStock() < 0) {
			throw new ProductStockInvalidException();
		}
		if (productDTO.getUnitPrice() == null) {
			throw new ProductUnitPriceRequiredException();
		}		
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		this.productRepo.save(product);
		return productDTO.getId();
	}
	
	
	@Override
	public void deleteById(Optional<Long> id) {
		
		if (!id.isPresent()) {
			throw new ProductNotFoundException("Product" +id.get()+" "+" Not found");
		}
		Optional<Product> _product  = this.productRepo.findById(id.get());
		if (!_product.isPresent()) {
			throw new ProductNotFoundException("Product" +id.get()+" "+" Not found");
		}
		
		productRepo.deleteById(id.get());
	}



	@Override
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	
}
