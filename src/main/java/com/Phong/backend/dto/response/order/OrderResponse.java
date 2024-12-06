package com.Phong.backend.dto.response.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {
    private String orderId;      // ID của đơn hàng
    private String status;     // Trạng thái đơn hàng (ví dụ: "PENDING")
    private Double totalPrice; // Tổng giá trị của đơn hàng trước khi tính phí vận chuyển
    private Double totalAmount;// Tổng giá trị của đơn hàng (bao gồm phí vận chuyển)
    private Double shippingFee;// Phí vận chuyển
    private String orderDate;  // Ngày giờ đặt hàng
}

