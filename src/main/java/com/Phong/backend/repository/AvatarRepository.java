package com.Phong.backend.repository;


import com.Phong.backend.entity.customer.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
