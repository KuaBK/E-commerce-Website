package com.Phong.backend.entity.invoice;

import com.Phong.backend.entity.employee.Seller;
import com.Phong.backend.entity.order.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private String id;

    private long orderCode;
    private double amount;
    private double amountRemaining;

    private Date createdAt;

    private String status;

}
