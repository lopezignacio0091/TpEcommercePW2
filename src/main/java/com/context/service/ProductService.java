package com.context.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.context.model.Product;
import com.context.model.dto.ProductDTO;


@Service
public interface ProductService {

	List<ProductDTO> getAll();
	ProductDTO getById(Long id);
	Long post(ProductDTO product);
	Long put (Optional<Long> id, ProductDTO product_dto);
	void deleteById(Optional<Long> id);
	void saveProduct (Product product);

}
