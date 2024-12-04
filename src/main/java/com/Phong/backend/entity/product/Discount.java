package com.Phong.backend.entity.product;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotions")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId; // Mã khuyến mãi

    private String name; // Tên khuyến mãi

    private double discountValue; // Giá trị khuyến mãi (có thể là % hoặc số tiền)

    @Column(nullable = false)
    private LocalDate startDate; // Ngày bắt đầu khuyến mãi

    @Column(nullable = false)
    private LocalDate endDate; // Ngày kết thúc khuyến mãi

    @ManyToMany
    @JoinTable(
            name = "promotion_products",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>(); // Các sản phẩm được áp dụng khuyến mãi
}
