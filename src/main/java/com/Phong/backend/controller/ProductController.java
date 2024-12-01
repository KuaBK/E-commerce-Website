package com.Phong.backend.controller;

import com.Phong.backend.dto.request.product.ProductRequest;
import com.Phong.backend.dto.request.product.ProductUpdateRequest;
import com.Phong.backend.dto.response.ApiResponse;
import com.Phong.backend.dto.response.product.ProductResponse;
import com.Phong.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PatchMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.updateProduct(productId, productUpdateRequest);
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<ProductResponse>> getAllProductsByCategory(@PathVariable Long categoryId) {
        return productService.getAllProductsByCategory(categoryId);
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/all")
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/{productId}/category/{categoryId}")
    public ApiResponse<ProductResponse> addProductToCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        return productService.addProductToCategory(productId, categoryId);
    }

    // Endpoint để xóa sản phẩm khỏi danh mục
    @DeleteMapping("/{productId}/category")
    public ApiResponse<ProductResponse> removeProductFromCategory(@PathVariable Long productId) {
        return productService.removeProductFromCategory(productId);
    }

    // Endpoint để tìm kiếm sản phẩm
    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
}
