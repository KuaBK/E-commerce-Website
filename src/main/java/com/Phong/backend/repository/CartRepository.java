package com.Phong.backend.repository;

import com.Phong.backend.entity.cart.Cart;
import com.Phong.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
