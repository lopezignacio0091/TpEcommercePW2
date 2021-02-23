package com.context.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.jca.cci.core.support.CciDaoSupport;
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
	 public Boolean productoProcces = true ;
	
	
	
	  public ReportServiceImpl(ReportRepository repoReport  , ProductService productService,CartService cartService) { 
		  super(); 
		  this.repoReport = repoReport; 
		  this.productService = productService;
		  this.cartService = cartService;
		 
	  }
	 
	

	@Override
	public void postProcessedCarts() {		
	List<Cart> carts = cartService.getCartsOrderByCheckoutDate();
	Collections.reverse(carts);
	carts.parallelStream().forEach(x->{
		proccesProductCart(x);
	});;
	
}
	
	 public   void proccesProductCart(Cart cart) {
		 Set<Product> productosSinStock = new HashSet<Product>();
		 Report cartProcesados = new Report();
		 boolean success = true;
		 for(CartProduct cartP:cart.getProducts()) {	
			  if(cartP.getQuantity()>cartP.getProduct().getStock()) {
				  success = false;
			  }
			  if (cartP.getProduct().getStock() == 0) {
				  productosSinStock.add(cartP.getProduct()); 
			  }
		 }	  
		 if(success) {
			 	cart.getProducts().parallelStream().forEach(x->{discountingStock(x);});
				cartProcesados.setProfit(cartProcesados.getProfit().add(cart.getTotal()));
				cart.setStatus(Status.PROCESSED);
			}
			if(!success) {
				cart.setStatus(Status.FAILED);
				cartProcesados.setTotalCartsFailed(cartProcesados.getTotalCartsFailed() + 1);
			}
		 cartProcesados.setTotalCartsProcessed(cartProcesados.getTotalCartsProcessed() + 1);
		 cartService.saveCart(cart);
		 System.out.println(cart.getId() + " Esta en estado " + cart.getStatus()); 
		 cartProcesados.setWithoutStockProducts(productosSinStock);
		 repoReport.save(cartProcesados);
	 }
	 


 public void discountingStock(CartProduct cartP) { 
	 cartP.getProduct().setStock(cartP.getProduct().getStock() - cartP.getQuantity());
	 productService.saveProduct(cartP.getProduct());
 	}
 }