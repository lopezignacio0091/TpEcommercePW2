package com.context.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.context.model.CartProductKey;

@Entity
public class CartProduct {
	
	@EmbeddedId
	CartProductKey id;
	
	
	@ManyToOne
    @MapsId("CART_ID")
    @JoinColumn(name = "CART_ID")
	@JsonBackReference
    Cart cart;
	
	@ManyToOne
    @MapsId("PRODUCT_ID")
    @JoinColumn(name = "PRODUCT_ID")
    Product product;
	
	int quantity;
	
	
	
	BigDecimal precio;

	public CartProduct() {
		super();
	}
	
	
	public CartProduct(Long idCart  , Long idProduct) {
		super();
		this.id = new CartProductKey(idCart,idProduct);
	}
	
	
	public CartProduct(Long idCart  , Long idProduct , int quantity ,BigDecimal precio ) {
		super();
		this.id = new CartProductKey(idCart,idProduct);
		this.precio = precio;
		this.quantity = quantity; 
		
	}

	public CartProductKey getId() {
		return id;
	}

	public void setId(CartProductKey id) {
		this.id = id;
	}

	public CartProduct(CartProductKey id) {
		super();
		this.id = id;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int stock) {
		this.quantity = stock;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
	
		if (obj instanceof CartProduct) {
			
			CartProduct cartProduct = (CartProduct) obj;
			
			return ((cartProduct.id.cartId == this.id.cartId) && (cartProduct.id.productId == this.id.productId));
			
		}
		
		return false;
	}

}