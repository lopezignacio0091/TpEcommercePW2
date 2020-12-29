package com.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.context.model.CartProduct;
import com.context.model.CartProductKey;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {

}
