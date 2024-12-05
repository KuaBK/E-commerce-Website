package com.Phong.backend.repository;

import com.Phong.backend.entity.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // Tìm tất cả OrderDetails theo orderId
    List<OrderDetail> findByOrderOrderId(String orderId);

    // Tìm tất cả OrderDetails theo productId (nếu cần thiết)
    List<OrderDetail> findByProductProductId(Long productId);

}
