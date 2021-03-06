package com.context.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.context.model.dto.ProductDTO;

@Service
public interface ProductService {

	List<ProductDTO> getAll();
	ProductDTO getById(Long id);
	Long post(ProductDTO product);
	Long put (ProductDTO product);
	void deleteById(Long id);

}
