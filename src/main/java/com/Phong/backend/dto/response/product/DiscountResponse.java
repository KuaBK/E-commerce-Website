package com.Phong.backend.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    private Long discountId;
    private String name;
    private String description;
    private double discountValue;
    private String startDate;
    private String endDate;
    private List<String> productNames; // Danh sách tên sản phẩm
}
