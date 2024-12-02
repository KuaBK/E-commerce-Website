package com.Phong.backend.service;

import com.Phong.backend.dto.response.ApiResponse;
import com.Phong.backend.entity.cart.Cart;
import com.Phong.backend.entity.cart.CartItem;
import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.customer.Customer;
import com.Phong.backend.entity.order.Order;
import com.Phong.backend.entity.order.OrderDetail;
import com.Phong.backend.exception.AppException;
import com.Phong.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order createOrderFromCart(Long customerId, List<Long> cartItemIds, Long addressId) {
        // Lấy giỏ hàng của khách hàng
        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer"));


        // Lấy các CartItem từ cart
        List<CartItem> selectedCartItems = cartItemRepository.findAllById(cartItemIds);
        if (selectedCartItems.isEmpty()) {
            throw new RuntimeException("No items selected for order");
        }

        Address deliveryAddress = cart.getCustomer().getAddresses().stream()
                .filter(address -> address.getAddressId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Delivery address not found for customer"));

        // Tạo đơn hàng mới từ các CartItem đã chọn
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setShippingFee(25000.0);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderDate(java.time.LocalDateTime.now());
        order.setStatus("PENDING");  // Trạng thái đơn hàng mới là "PENDING"

        // Lấy chi tiết đơn hàng từ các CartItem đã chọn
        double totalAmount = 0;
        for (CartItem cartItem : selectedCartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice());
            totalAmount += cartItem.getTotalPrice();
            order.getOrderDetails().add(orderDetail);  // Không còn lỗi NullPointerException
        }

        order.setTotalAmount(totalAmount + order.getShippingFee());

        // Lưu đơn hàng vào cơ sở dữ liệu
        order = orderRepository.save(order);

        // Sau khi đơn hàng được tạo, xóa các CartItem đã chọn khỏi giỏ hàng
        cart.setTotalPrice(cart.getTotalPrice() - totalAmount);
        cart.getItems().removeAll(selectedCartItems);
        cartRepository.save(cart);  // Cập nhật lại giỏ hàng

        // Xóa các CartItem khỏi cơ sở dữ liệu
        cartItemRepository.deleteAll(selectedCartItems);

        return order;
    }



    public ApiResponse<List<Order>> getOrdersByCustomer(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            return ApiResponse.<List<Order>>builder()
                    .code(404)
                    .message("Customer not found")
                    .build();
        }

        Customer customer = customerOpt.get();
        List<Order> orders = orderRepository.findByCustomer(customer);

        return ApiResponse.<List<Order>>builder()
                .message("Orders retrieved successfully")
                .result(orders)
                .build();
    }

    public ApiResponse<Order> getOrderDetails(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            return ApiResponse.<Order>builder()
                    .code(404)
                    .message("Order not found")
                    .build();
        }

        Order order = orderOpt.get();
        return ApiResponse.<Order>builder()
                .message("Order details retrieved successfully")
                .result(order)
                .build();
    }

    public ApiResponse<Void> cancelOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            return ApiResponse.<Void>builder()
                    .code(404)
                    .message("Order not found")
                    .build();
        }

        Order order = orderOpt.get();
        if ("CANCELLED".equals(order.getStatus())) {
            return ApiResponse.<Void>builder()
                    .code(400)
                    .message("Order is already cancelled")
                    .build();
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return ApiResponse.<Void>builder()
                .message("Order cancelled successfully")
                .build();
    }
}

