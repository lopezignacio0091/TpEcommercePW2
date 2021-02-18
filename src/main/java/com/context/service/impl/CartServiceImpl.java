package com.context.service.impl;

import com.context.model.Cart;
import com.context.model.CartProduct;
import com.context.model.CartProductKey;
import com.context.model.Product;
import com.context.utils.*;
import com.context.service.business.exception.*;
import com.context.model.dto.CartDTO;
import com.context.model.dto.CartProductDTO;
import com.context.model.dto.ProductDTO;
import com.context.repository.CartProductRepository;
import com.context.repository.CartRepository;
import com.context.repository.ProductRepository;
import com.context.service.CartService;
import com.context.service.business.exception.CartDuplicatedException;
import com.context.service.business.exception.CartInsufficientException;
import com.context.service.business.exception.CartNotFoundException;
import com.context.service.business.exception.CartServiceException;
import com.context.service.business.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepo;
	private final ProductRepository productRepo;
	private final CartProductRepository cartProductRepo;

	public CartServiceImpl(CartRepository cartRepo, ProductRepository productRepo,CartProductRepository cartProductRepo) {
		super();
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.cartProductRepo = cartProductRepo;
	}

	@Override
	public CartDTO getById(Long id) {
		CartDTO cartDTO = new CartDTO();
		Optional<Cart> cart = cartRepo.findById(id);
		if (cart.isPresent()) {
			BeanUtils.copyProperties(cart.get(), cartDTO);
			return cartDTO;
		}
		throw new CartNotPresentException();
	}

	@Override
	public Long post(CartDTO cartDTO) {
		
		if (cartDTO.getfullName() == null || cartDTO.getfullName()== ""){
			throw new CartFullNameRequiredException();
		}
		if (cartDTO.getEmail() == null || cartDTO.getEmail() == "" ) {
			throw new CartEmailRequiredException();
		}

		Optional<Cart> carrito = cartRepo.findByEmail(cartDTO.getEmail());
		if (carrito.isPresent()) {
				throw new CartDuplicatedException("Duplicated cart");
			}
				Cart cart = new Cart();
				BeanUtils.copyProperties(cartDTO, cart);
				cart = cartRepo.save(cart);
				return cart.getId();

		}
	

	@Override
	public Long postId(Long id, CartProductDTO cartproductDTO) {
		
		
		if(id == 0 || id ==null ) {
			throw new CartIdRequiredException();
		}
		CartProduct producNew = new CartProduct();
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Product> product = productRepo.findById(cartproductDTO.getId());
		
		if (cartproductDTO.getQuantity() == null) {
			throw new ProductQuantityRequiredException();
		}
		if (cartproductDTO.getQuantity() <= 0) {
			throw new ProductQuantityInvalidException();
		}		

		if (!product.isPresent()) {throw new ProductNotPresentException();}
		if (!cart.isPresent()) {throw new CartNotPresentException();}

		if (cartproductDTO.getQuantity() > product.get().getStock()){throw new ProductStockInsufficientException();} 

		CartProductKey key = new CartProductKey(cart.get().getId(), product.get().getId());
		Optional<CartProduct> cartProductExiste = cartProductRepo.findById(key);
					
		if (cartProductExiste.isPresent()) {
			cartProductExiste.get().setQuantity(cartproductDTO.getQuantity() + cartProductExiste.get().getQuantity());
			return cartProductExiste.get().getCart().getId();
		}

						producNew.setId(key);
						producNew.setQuantity(cartproductDTO.getQuantity());
						producNew.setPrecio(product.get().getUnitPrice());

						cart.get().setCreationDate(LocalDate.now());

						BigDecimal totalNew = (product.get().getUnitPrice().multiply(BigDecimal.valueOf(cartproductDTO.getQuantity())));
						cart.get().setTotal((cart.get().getTotal().add(totalNew)));
						cart.get().setStatus(Status.NEW);
						
						product.get().setStock(product.get().getStock() - cartproductDTO.getQuantity());
						cartProductRepo.save(producNew);
						cartRepo.save(cart.get());
						
						return cart.get().getId();
} 
			
				

	@Override
	public void deleteProductCart(Long id, Long idProduct) {

		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Product> product = productRepo.findById(idProduct);
		
		if (!product.isPresent()) {	
			throw new ProductNotPresentException();
		}
		if (!cart.isPresent()) {
		throw new CartNotPresentException();	
		}
	
			CartProductKey key = new CartProductKey(cart.get().getId(), product.get().getId());
			Optional<CartProduct> cartProduct = cartProductRepo.findById(key);
			cart.get().setTotal(cart.get().getTotal().subtract(
			cartProduct.get().getPrecio().multiply(BigDecimal.valueOf(cartProduct.get().getQuantity()))));	
			cartProductRepo.deleteById(key);
		}

	@Override
	public List<CartDTO> getCarts() {

		List<Cart> cart = cartRepo.findAll();
		List<CartDTO> dtos = new ArrayList<>(cart.size());
		for (int i = 0; i < cart.size(); i++) {
			CartDTO cartDTO = new CartDTO();
			BeanUtils.copyProperties(cart.get(i), cartDTO);
			dtos.add(cartDTO);
		}

		return dtos;
	}

	@Override
	public String checkout(long id) {

		Optional<Cart> cart = cartRepo.findById(id);
		if (!cart.isPresent()) {
			throw new CartNotPresentException();
		}
		if (!cart.get().getStatus().equals(Status.NEW)) {
		throw new CartProcessingNotAllowDeletionException();	
		}
		
		Date currentDate = new Date();
		cart.get().setStatus(Status.READY);
		cart.get().setCheckDate(currentDate);
		cartRepo.save(cart.get());
		return "Checkout realizado";
	}

	@Override
	public List<ProductDTO> getProductsFromCart(long id) {

		Optional<Cart> carrito = cartRepo.findById(id);

		if (!carrito.isPresent()) {
			throw new CartNotPresentException();
		}
		List<CartProduct> products = new ArrayList<CartProduct>(carrito.get().getProducts());
		List<ProductDTO> dtos = new ArrayList<>(products.size());
		for (int i = 0; i < products.size(); i++) {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(products.get(i).getProduct(), productDTO);
			productDTO.setStock(products.get(i).getQuantity());
			dtos.add(productDTO);
		}
			return dtos;
	}

	@Override
	public List<Cart> getCartsOrderByCheckoutDate() {
		List <Cart> carrito = cartRepo.findAll();
		 Collections.sort(carrito);
		return carrito;
	}

	@Override
	public void saveCart(Cart cart) {
		cartRepo.save(cart);	
	}
	
	
	

}
