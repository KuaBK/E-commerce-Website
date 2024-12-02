package com.Phong.backend.controller;

import com.Phong.backend.dto.request.invoice.InvoiceRequest;
import com.Phong.backend.dto.response.ApiResponse;
import com.Phong.backend.dto.response.invoice.InvoiceDetailResponse;
import com.Phong.backend.dto.response.invoice.InvoiceResponse;
import com.Phong.backend.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InvoiceResponse>> createInvoice(@RequestBody InvoiceRequest requestDTO) {
        InvoiceResponse response = invoiceService.createInvoice(requestDTO);
        return ResponseEntity.ok(ApiResponse.<InvoiceResponse>builder()
                .message("Invoice created successfully")
                .result(response)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InvoiceResponse>> getInvoiceById(@PathVariable Long id) {
        InvoiceResponse response = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(ApiResponse.<InvoiceResponse>builder()
                .message("Invoice retrieved successfully")
                .result(response)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InvoiceResponse>>> getAllInvoices() {
        List<InvoiceResponse> responses = invoiceService.getAllInvoices();
        return ResponseEntity.ok(ApiResponse.<List<InvoiceResponse>>builder()
                .message("All invoices retrieved successfully")
                .result(responses)
                .build());
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelInvoice(@PathVariable Long id) {
        invoiceService.cancelInvoice(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Invoice cancelled successfully")
                .build());
    }

    @GetMapping("/details/{invoiceId}")
    public ResponseEntity<ApiResponse<List<InvoiceDetailResponse>>> getInvoiceDetails(@PathVariable Long invoiceId) {
        List<InvoiceDetailResponse> response = invoiceService.getInvoiceDetails(invoiceId);
        return ResponseEntity.ok(ApiResponse.<List<InvoiceDetailResponse>>builder()
                .message("Invoice details retrieved successfully")
                .result(response)
                .build());
    }
}
