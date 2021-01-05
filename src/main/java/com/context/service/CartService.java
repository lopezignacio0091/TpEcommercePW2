package com.context.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.context.model.Cart;
import com.context.model.dto.CartDTO;
import com.context.model.dto.CartProductDTO;
import com.context.model.dto.ProductDTO;

@Service
public interface CartService {
	
	CartDTO getById(Long id);
	Long post(CartDTO cartDTO);
	Long postId(Long id ,CartProductDTO cartProductDTO);
	List<CartDTO> getCarts();
	void deleteProductCart(Long id , Long idProduct);
	String checkout(long id);
	List<ProductDTO> getProductsFromCart(long id);
	List<Cart> getCartsOrderByCheckoutDate();
	void saveCart(Cart cart);
	
}