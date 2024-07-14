package com.example.typeformclone.controller;

import com.example.typeformclone.model.Product;
import com.example.typeformclone.model.UserPurchasedProduct;
import com.example.typeformclone.service.UserPurchasedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserPurchasedProductController {

    @Autowired
    private UserPurchasedProductService userPurchasedProductService;

    static class PurchaseRequest {
        private Long productId;

        public PurchaseRequest() {
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
    }

    @PostMapping("/{userId}/purchase")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long userId, @RequestBody PurchaseRequest request) {
        userPurchasedProductService.purchaseProduct(userId, request.getProductId());
        return ResponseEntity.ok("Product purchased successfully");
    }

    @GetMapping("/{userId}/purchased-products")
    public ResponseEntity<List<Product>> getPurchasedProducts(@PathVariable Long userId) {
        return ResponseEntity.ok(userPurchasedProductService.getPurchasedProductsByUserId(userId));
    }


    @GetMapping
    public ResponseEntity<List<UserPurchasedProductDTO>> getAllPurchasedProducts() {
        List<UserPurchasedProductDTO> purchasedProducts = userPurchasedProductService.getAllPurchasedProducts();
        return ResponseEntity.ok(purchasedProducts);
    }

    // DTO for purchased products
    public static class UserPurchasedProductDTO {
        private Long userId;
        private String username;
        private Long productId;
        private String productName;

        public UserPurchasedProductDTO(Long userId, String username, Long productId, String productName) {
            this.userId = userId;
            this.username = username;
            this.productId = productId;
            this.productName = productName;
        }

        // Getters and setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}


