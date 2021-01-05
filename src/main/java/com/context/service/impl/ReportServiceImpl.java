package com.context.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.context.model.Cart;
import com.context.model.CartProduct;
import com.context.model.Product;
import com.context.model.Report;
import com.context.model.dto.ReportDTO;
import com.context.repository.CartRepository;
import com.context.repository.ProductRepository;
import com.context.repository.ReportRepository;
import com.context.service.CartService;
import com.context.service.ProductService;
import com.context.service.ReportService;
import com.context.utils.Status;

@Service
public class ReportServiceImpl implements ReportService{
	
	 private final ReportRepository repoReport;
	 private final ProductService productService;
	 private final CartService cartService;
	
	
	  public ReportServiceImpl(ReportRepository repoReport  , ProductService productService,CartService cartService) { 
		  super(); 
		  this.repoReport = repoReport; 
		  this.productService = productService;
		  this.cartService = cartService;
	  }
	 
	@Override
	public void getProcessedCarts() {
			
		  List<Cart> carts = cartService.getCartsOrderByCheckoutDate(); 
		  Set<Product> productosSinStock = new HashSet<Product>();
		  Report cartProcesados = new Report(); 
		  cartProcesados.setProfit(BigDecimal.ZERO);
		  cartProcesados.setTotalCartsFailed(0);
		  cartProcesados.setTotalCartsFailed(0);
		  
		  
		  
		  for (Cart cart : carts) { 
			  if (cart.getStatus() == Status.READY) {
				  List<CartProduct> cartProducts = new ArrayList<CartProduct>(cart.getProducts()); 
				  boolean success = true;
		  
		  for (CartProduct cartP : cartProducts) { 
			  if (cartP.getQuantity() > cartP.getProduct().getStock())
			  { 
				  success = false;
			   } 
			  if (cartP.getProduct().getStock() == 0) {
				  productosSinStock.add(cartP.getProduct()); 
			}
		  
		  }
		  
		  if (success) { 
			for (CartProduct cartP : cartProducts) {	  
		  cartP.getProduct().setStock(cartP.getProduct().getStock()-cartP.getQuantity());
		  productService.saveProduct(cartP.getProduct()); 
		  }
		  cartProcesados.setProfit(cartProcesados.getProfit().add(cart.getTotal()));
		  cart.setStatus(Status.PROCESSED);
		  } 
		  else 
		  { 
			  cart.setStatus(Status.FAILED);
			  cartProcesados.setTotalCartsFailed(cartProcesados.getTotalCartsFailed() + 1);
		  }
		  
		  cartProcesados.setTotalCartsProcessed(cartProcesados.getTotalCartsProcessed()+1); 
//		  cartProcesados.setWithoutStockProducts(productosSinStock);
		  cartProcesados.setWithoutStockProducts(productosSinStock);
		  cartProcesados.setProcessedDateTime(LocalDate.now());
		  cartService.saveCart(cart);
		  repoReport.save(cartProcesados);
		  System.out.println(cart.getId() +" Esta en estado " + cart.getStatus()); 
		  } 
		  
		}
		 
	}





	@Override
	public void msj() {
		// TODO Auto-generated method stub
		System.out.println("hola");
	}
	
}
