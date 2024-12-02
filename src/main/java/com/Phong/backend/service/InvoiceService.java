package com.Phong.backend.service;

import com.Phong.backend.dto.request.invoice.InvoiceRequest;
import com.Phong.backend.dto.response.invoice.InvoiceDetailResponse;
import com.Phong.backend.dto.response.invoice.InvoiceResponse;
import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.customer.Customer;
import com.Phong.backend.entity.invoice.Invoice;
import com.Phong.backend.entity.invoice.InvoiceDetail;
import com.Phong.backend.entity.invoice.InvoiceStatus;
import com.Phong.backend.entity.invoice.PaymentMethod;
import com.Phong.backend.entity.order.Order;
import com.Phong.backend.repository.InvoiceDetailRepository;
import com.Phong.backend.repository.InvoiceRepository;
import com.Phong.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final OrderRepository orderRepository;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          InvoiceDetailRepository invoiceDetailRepository,
                          OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequest requestDTO) {
        // Lấy đơn hàng dựa trên orderId
        Order order = orderRepository.findById(requestDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Lấy danh sách địa chỉ của khách hàng

        // Tạo hóa đơn
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setDeliveryAddress(order.getDeliveryAddress());
        invoice.setIssuedDate(LocalDateTime.now());
        invoice.setShippingFee(order.getShippingFee());
        invoice.setTotalAmount(order.getTotalAmount());
        invoice.setPaymentMethod(PaymentMethod.BANK);
        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setCreatedAt(LocalDateTime.now());

        invoice = invoiceRepository.save(invoice);

        Invoice savedInvoice = invoiceRepository.save(invoice);
        // Tạo chi tiết hóa đơn
        List<InvoiceDetail> invoiceDetails = order.getOrderDetails().stream().map(orderDetail -> {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setInvoice(savedInvoice);
            detail.setProduct(orderDetail.getProduct());
            detail.setQuantity(orderDetail.getQuantity());
            detail.setUnitPrice(orderDetail.getPrice());
            detail.setTotalPrice(orderDetail.getPrice() * orderDetail.getQuantity());
            return detail;
        }).collect(Collectors.toList());

        invoiceDetailRepository.saveAll(invoiceDetails);

        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .deliveryAddress(invoice.getDeliveryAddress())
                .issuedDate(invoice.getIssuedDate())
                .status(invoice.getStatus())
                .totalAmount(invoice.getTotalAmount())
                .shippingFee(invoice.getShippingFee())
                .paymentMethod(invoice.getPaymentMethod())
                .build();
    }


    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .deliveryAddress(invoice.getDeliveryAddress())
                .issuedDate(invoice.getIssuedDate())
                .status(invoice.getStatus())
                .totalAmount(invoice.getTotalAmount())
                .shippingFee(invoice.getShippingFee())
                .paymentMethod(invoice.getPaymentMethod())
                .build();
    }

    public void cancelInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setStatus(InvoiceStatus.CANCELLED);
        invoiceRepository.save(invoice);
    }

    public List<InvoiceDetailResponse> getInvoiceDetails(Long invoiceId) {
        return invoiceDetailRepository. findByInvoice_InvoiceId(invoiceId).stream()
                .map(detail -> InvoiceDetailResponse.builder()
                        .invoiceDetailId(detail.getInvoiceDetailId())
                        .productName(detail.getProduct().getName())
                        .quantity(detail.getQuantity())
                        .unitPrice(detail.getUnitPrice())
                        .totalPrice(detail.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> InvoiceResponse.builder()
                        .invoiceId(invoice.getInvoiceId())
                        .deliveryAddress(invoice.getDeliveryAddress())
                        .issuedDate(invoice.getIssuedDate())
                        .status(invoice.getStatus())
                        .totalAmount(invoice.getTotalAmount())
                        .shippingFee(invoice.getShippingFee())
                        .paymentMethod(invoice.getPaymentMethod())
                        .build())
                .collect(Collectors.toList());
    }
}
