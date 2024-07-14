package com.example.typeformclone.service;

import com.example.typeformclone.controller.UserPurchasedProductController;
import com.example.typeformclone.model.Product;
import com.example.typeformclone.model.User;
import com.example.typeformclone.model.UserPurchasedProduct;
import com.example.typeformclone.repository.ProductRepository;
import com.example.typeformclone.repository.UserPurchasedProductRepository;
import com.example.typeformclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPurchasedProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void purchaseProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        user.getPurchasedProducts().add(product);
        userRepository.save(user);
    }

    public List<Product> getPurchasedProductsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPurchasedProducts();
    }


    public List<UserPurchasedProductController.UserPurchasedProductDTO> getAllPurchasedProducts() {
        List<UserPurchasedProductController.UserPurchasedProductDTO> purchasedProducts = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (Product product : user.getPurchasedProducts()) {
                purchasedProducts.add(new UserPurchasedProductController.UserPurchasedProductDTO(
                        user.getId(), user.getUsername(), product.getId(), product.getName()));
            }
        }
        return purchasedProducts;
    }
}
