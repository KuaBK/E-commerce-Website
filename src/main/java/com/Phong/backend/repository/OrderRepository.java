package com.Phong.backend.repository;

import com.Phong.backend.entity.order.Order;
import com.Phong.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByCustomer(Customer customer);

}
