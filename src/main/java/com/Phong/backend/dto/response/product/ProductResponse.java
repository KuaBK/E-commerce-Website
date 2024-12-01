package com.Phong.backend.dto.response.product;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String origin;
    private String version;
    private String evaluate;
    private List<String> images;
    private Integer stockQuantity;
    private String categoryName;
}
