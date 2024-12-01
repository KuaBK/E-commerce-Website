package com.Phong.backend.entity.invoice;

import com.Phong.backend.entity.employee.Seller;
import com.Phong.backend.entity.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Invoice invoice; // Liên kết với hóa đơn

    private Long amountPaid;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // Trạng thái thanh toán (SUCCESS, FAILED, PENDING)

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // Phương thức thanh toán (COD, CARD, PAYPAL)
}
