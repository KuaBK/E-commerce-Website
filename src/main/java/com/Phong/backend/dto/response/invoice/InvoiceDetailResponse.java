package com.Phong.backend.dto.response.invoice;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponse {
    private Long invoiceDetailId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
}
