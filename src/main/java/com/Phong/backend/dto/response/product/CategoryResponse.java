package com.Phong.backend.dto.response.product;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long categoryId;
    private String name;
    private String description;
    private List<String> productNames;
}
