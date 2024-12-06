package com.Phong.backend.repository;

import com.Phong.backend.entity.product.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}