package com.context.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.context.service.ReportService;
import com.context.utils.Status;

@Service
public class ReportServiceImpl implements ReportService{
	
	 private final ReportRepository repoReport;
	 private final CartRepository cartReport; 
	 private final ProductRepository productRepo;
	 
	
	
	  public ReportServiceImpl(ReportRepository repoReport , CartRepository cartRepo , ProductRepository productRepo) { 
		  super(); 
		  this.repoReport = repoReport; 
		  this.cartReport = cartRepo; 
		  this.productRepo = productRepo;
		  
	  }
	 
	@Override
	public void getProcessedCarts() {
		
		  List<Cart> cart = cartReport.findAll(); 
		  Set<Product> productosSinStock = new HashSet<Product>();
		  Report cartProcesados = new Report(); 
		  cartProcesados.setProfit(BigDecimal.ZERO);
		  cartProcesados.setTotalCartsFailed(0);
		  cartProcesados.setTotalCartsFailed(0);
		  
		  for (Cart carts : cart) { 
			  if (carts.getStatus() == Status.READY) {
				  List<CartProduct> cartProducts = new ArrayList<CartProduct>(carts.getProducts()); 
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
		  productRepo.save(cartP.getProduct()); 
		  }
		  cartProcesados.setProfit(cartProcesados.getProfit().add(carts.getTotal()));
		  carts.setStatus(Status.PROCESSED);
		  } 
		  else 
		  { 
			  carts.setStatus(Status.FAILED);
			  cartProcesados.setTotalCartsFailed(cartProcesados.getTotalCartsFailed() + 1);
		  }
		  
		  cartProcesados.setTotalCartsProcessed(cartProcesados.getTotalCartsProcessed()+1); 
//		  cartProcesados.setWithoutStockProducts(productosSinStock);
		  cartProcesados.setWithoutStockProducts(productosSinStock);
		  cartProcesados.setProcessedDateTime(LocalDate.now());
		  cartReport.save(carts);
		  repoReport.save(cartProcesados);
		  System.out.println(carts.getId() +" Esta en estado " + carts.getStatus()); 
		  } 
		  
		}
		 
	}





	@Override
	public void msj() {
		// TODO Auto-generated method stub
		System.out.println("hola");
	}
	
}
