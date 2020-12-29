package com.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.context.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
}
