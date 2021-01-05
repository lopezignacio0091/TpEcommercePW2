package com.context.service.impl;

import com.context.model.Cart;
import com.context.model.CartProduct;
import com.context.model.CartProductKey;
import com.context.model.Product;
import com.context.utils.*;
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
		throw new CartNotFoundException("Invalid cart");
	}

	@Override
	public Long post(CartDTO cartDTO) {

		Boolean camposVacios = (cartDTO.getEmail() == null || cartDTO.getEmail() == "" || cartDTO.getfullName() == null
				|| cartDTO.getfullName() == "") ? true : false;

		if (camposVacios) {
			return null;
		} else {

			Optional<Cart> carrito = cartRepo.findByEmail(cartDTO.getEmail());
			if (carrito.isPresent() == false) {

				Cart cart = new Cart();
				cartDTO.setCreationDate(LocalDate.now());
				cartDTO.setStatus(Status.NEW);
				BigDecimal totalNew = BigDecimal.valueOf(0);
				cartDTO.setTotal(totalNew);
				BeanUtils.copyProperties(cartDTO, cart);
				cart = cartRepo.save(cart);
				return cart.getId();
			} else {
				throw new CartDuplicatedException("Duplicated cart");
			}
		}
	}

	@Override
	public Long postId(Long id, CartProductDTO cartproductDTO) {

		CartProduct producNew = new CartProduct();
		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Product> product = productRepo.findById(cartproductDTO.getId());

		if (product.isPresent()) {

			if (cart.isPresent()) {

				if (cartproductDTO.getQuantity() > product.get().getStock()) {
					throw new CartInsufficientException("Insufficient stock");
				} else {

					CartProductKey key = new CartProductKey(cart.get().getId(), product.get().getId());
					Optional<CartProduct> cartProductExiste = cartProductRepo.findById(key);
					
					if (cartProductExiste.isPresent() == false) {

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
					} else {
						cartProductExiste.get().setQuantity(cartproductDTO.getQuantity() + cartProductExiste.get().getQuantity());
						
						return cartProductExiste.get().getCart().getId();
					}

				}
			} else {
				throw new CartNotFoundException("Invalid cart");
			}
		} else {
			throw new CartNotFoundException("Invalid product");
		}
	}

	@Override
	public void deleteProductCart(Long id, Long idProduct) {

		Optional<Cart> cart = cartRepo.findById(id);
		Optional<Product> product = productRepo.findById(idProduct);
		Boolean cartValue = false;
		Boolean productValue = false;

		if (product.isPresent()) {
			productValue = true;

		} else {
			throw new ProductNotFoundException("Invalid product Not found");
		}
		if (cart.isPresent()) {
			cartValue = true;
		} else {
			throw new CartNotFoundException("Invalid Cart  Not found");
		}
		if (productValue == true && cartValue == true) {
			CartProductKey key = new CartProductKey(cart.get().getId(), product.get().getId());
			Optional<CartProduct> cartProduct = cartProductRepo.findById(key);

			cart.get().setTotal(cart.get().getTotal().subtract(
			cartProduct.get().getPrecio().multiply(BigDecimal.valueOf(cartProduct.get().getQuantity()))));
			
			cartProductRepo.deleteById(key);
		}
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

		if (cart.isPresent()) {
			if (cart.get().getStatus().equals(Status.NEW)) {

				Date currentDate = new Date();
				cart.get().setStatus(Status.READY);

				cart.get().setCheckDate(currentDate);
				cartRepo.save(cart.get());

				return "Checkout realizado";
			} else {
				throw new CartNotFoundException("Invalid cart status");
			}
		} else {
			throw new CartNotFoundException("Invalid Cart");
		}

	}

	@Override
	public List<ProductDTO> getProductsFromCart(long id) {

		Optional<Cart> carrito = cartRepo.findById(id);

		if (carrito.isPresent()) {
			List<CartProduct> products = new ArrayList<CartProduct>(carrito.get().getProducts());
			List<ProductDTO> dtos = new ArrayList<>(products.size());

			for (int i = 0; i < products.size(); i++) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(products.get(i).getProduct(), productDTO);

				productDTO.setStock(products.get(i).getQuantity());
				dtos.add(productDTO);
			}
			return dtos;
		} else {
			throw new CartServiceException("Carrito inexistente");
		}
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
