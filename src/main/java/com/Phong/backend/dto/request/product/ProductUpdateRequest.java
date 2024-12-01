package com.Phong.backend.dto.request.product;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {
    private String name;
    private String description;
    private Double price;
    private String origin;
    private String version;
    private String evaluate;
    private List<String> images;
    private Integer stockQuantity;
    private Long categoryId;
}
