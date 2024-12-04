package com.Phong.backend.controller;

import com.Phong.backend.dto.response.ApiResponse;
import com.Phong.backend.dto.response.customer.LoyaltyResponse;
import com.Phong.backend.entity.customer.Loyalty;
import com.Phong.backend.repository.LoyaltyRepository;
import com.Phong.backend.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyService;


    @GetMapping("/{customerId}")
    public ApiResponse<LoyaltyResponse> getLoyaltyInfo(@PathVariable Long customerId) {
        return loyaltyService.getLoyaltyInfo(customerId);
    }
}
