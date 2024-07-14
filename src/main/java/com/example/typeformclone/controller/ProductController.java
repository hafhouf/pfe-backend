package com.example.typeformclone.controller;

import com.example.typeformclone.model.Product;
import com.example.typeformclone.model.User;
import com.example.typeformclone.repository.ProductRepository;
import com.example.typeformclone.repository.UserRepository;
import com.example.typeformclone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/purchase")
    public ResponseEntity<?> purchaseProduct(@PathVariable Long userId, @RequestBody PurchaseRequest purchaseRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(purchaseRequest.getProductId());

        if (userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();

            if (user.getPoints() >= product.getPrice()) {
                user.setPoints(user.getPoints() - product.getPrice());
                userRepository.save(user);
                return ResponseEntity.ok("Product purchased successfully");
            } else {
                return ResponseEntity.badRequest().body("Not enough points to purchase this product");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public static class PurchaseRequest {
        private Long productId;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
    }




}
