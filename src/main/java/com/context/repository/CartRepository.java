package com.context.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.context.model.Cart;
import com.context.utils.Status;

public interface CartRepository extends JpaRepository<Cart, Long>{

	 public List<Cart> findByOrderByCheckDateAsc();
	 Optional <Cart> findByEmail(String email);
	 List <Cart> findByStatus(Status ready);
}
