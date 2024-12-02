package com.Phong.backend.controller;

import com.Phong.backend.dto.response.ApiResponse;
import com.Phong.backend.entity.order.Order;
import com.Phong.backend.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

        @PostMapping("/createOrder")
        public ApiResponse<Order> createOrder(
                @RequestParam Long customerId,
                @RequestParam Long addressId,
                @RequestBody List<Long> cartItemIds) {
            Order order = orderService.createOrderFromCart(customerId, cartItemIds, addressId);
            return ApiResponse.<Order>builder()
                    .message("Order created successfully")
                    .result(order)
                    .build();
        }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersByCustomer(@RequestParam Long customerId) {
        ApiResponse<List<Order>> response = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.status(response.getCode() == 1000 ? 200 : 404).body(response);
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<Order>> getOrderDetails(@RequestParam Long id) {
        ApiResponse<Order> response = orderService.getOrderDetails(id);
        return ResponseEntity.status(response.getCode() == 1000 ? 200 : 404).body(response);
    }

    @PutMapping("/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelOrder(@RequestParam Long id) {
        ApiResponse<Void> response = orderService.cancelOrder(id);
        return ResponseEntity.status(response.getCode() == 1000 ? 200 : 400).body(response);
    }
}
