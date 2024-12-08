package com.Phong.backend.repository;

import com.Phong.backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByNameContainingIgnoreCase(String nameKeyword);

    @Query("SELECT p FROM Product p JOIN FETCH p.discounts WHERE p.productId = :productId")
    Optional<Product> findByIdWithDiscounts(@Param("productId") Long productId);
}
