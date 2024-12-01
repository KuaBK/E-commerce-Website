package com.Phong.backend.entity.invoice;

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

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Seller seller;

    private LocalDateTime issuedDate;
    private String deliveryAddress;

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
    private boolean paymentStatus;
    private double totalAmount;

    @OneToOne
    private Payment payments;
}
