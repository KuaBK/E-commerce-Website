package com.Phong.backend.repository;

import com.Phong.backend.entity.customer.Address;
import com.Phong.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomer(Customer customer);
}
