package com.context.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class CartProductKey implements Serializable {
	
	
	private static final long serialVersionUID = 1401258252949596833L;

	@Column(name = "CART_ID")
    Long cartId;
 
    @Column(name = "PRODUCT_ID")
    Long productId;

    
    
    public CartProductKey() {
		super();
	}
    
	public CartProductKey(Long cartId, Long productId) {
		this.cartId = cartId;
		this.productId = productId;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
		public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}


}
