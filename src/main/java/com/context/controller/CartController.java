package com.context.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.context.model.dto.CartDTO;
import com.context.model.dto.CartProductDTO;
import com.context.model.dto.ProductDTO;
import com.context.service.CartService;

@RestController
@Validated
public class CartController {

		private final CartService service;
		
		public CartController(CartService service) {
			super();
			this.service = service;
		}
		
		@GetMapping(value = "/carts/{id}")
		public ResponseEntity<CartDTO> getCartById(@PathVariable long id) {
			return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
		}
		
		
		@PostMapping(path = "/carts")
		public ResponseEntity<Object> postCart(@Valid @RequestBody CartDTO cartDTO ) {
			Long id = service.post(cartDTO);		
			return new ResponseEntity<>("Cart successfully Created. Id: " + id, HttpStatus.CREATED);					
		}	
				
	@PostMapping(path = "/carts/{id}/products")
		public ResponseEntity<Object> postProductToCart(@PathVariable long id, @RequestBody CartProductDTO cartproductDTO){
			
				Long Id = service.postId(id, cartproductDTO);
				return new ResponseEntity<>("Product add in Cart" + ": " +Id, HttpStatus.OK);
			}
	
		
		@DeleteMapping(path = "/carts/{id}/products/{idProduct}")
		public ResponseEntity<Object> postProductToCart(@PathVariable long id, @PathVariable long idProduct ){
				service.deleteProductCart(id, idProduct);
				return new ResponseEntity<>("Delete Product " + ": ", HttpStatus.OK);
			}
		
		@GetMapping(value = "/carts")
		public ResponseEntity<List<CartDTO>> getCartsCreated() {
			return new ResponseEntity<>(service.getCarts(), HttpStatus.OK);
		}
		
		@PostMapping(value = "/carts/{id}/checkout")
	    public ResponseEntity<Object> checkout(@PathVariable long id) { 	
	    	return new ResponseEntity<>(service.checkout(id), HttpStatus.OK);
	    }
		
		
		@GetMapping(value = "/carts/{id}/products")
	    public ResponseEntity<List<ProductDTO>>getProductsFromCart(@PathVariable long id) {
	        return new ResponseEntity<List<ProductDTO>>(service.getProductsFromCart(id), HttpStatus.OK);
	    }
		
		
		
				
}