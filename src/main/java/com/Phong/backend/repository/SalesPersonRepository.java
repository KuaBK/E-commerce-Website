package com.Phong.backend.repository;

import com.Phong.backend.entity.employee.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPersonRepository extends JpaRepository<Seller, Long> {
}
