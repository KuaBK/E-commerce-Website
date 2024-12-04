package com.Phong.backend.repository;

import com.Phong.backend.entity.customer.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {
    Loyalty findByCustomer_CustomerId(Long customerId);
}

