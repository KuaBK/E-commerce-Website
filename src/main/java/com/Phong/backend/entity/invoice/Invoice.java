package com.Phong.backend.entity.invoice;

import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.employee.Seller;
import com.Phong.backend.entity.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    private LocalDateTime issuedDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> details;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDateTime createdAt;
    private double shippingFee;
    private double totalAmount;
}
