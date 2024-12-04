package com.Phong.backend.entity.order;

import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    private double totalPrice;
    private double shippingFee;
    private double totalAmount;

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;
}
