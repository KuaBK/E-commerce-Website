package com.Phong.backend.repository;

import com.Phong.backend.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Optional<Invoice> findByOrder_OrderId(String orderId);
}
