package com.Phong.backend.dto.response.invoice;

import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.invoice.InvoiceStatus;
import com.Phong.backend.entity.invoice.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private Long invoiceId;
    private Address deliveryAddress;
    private LocalDateTime issuedDate;
    private InvoiceStatus status;
    private PaymentMethod paymentMethod;
    private double totalAmount;
    private double shippingFee;
}
