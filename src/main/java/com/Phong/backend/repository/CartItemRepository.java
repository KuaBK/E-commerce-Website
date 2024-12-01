package com.Phong.backend.repository;

import com.Phong.backend.entity.cart.Cart;
import com.Phong.backend.entity.cart.CartItem;
import com.Phong.backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
