package com.example.typeformclone.repository;

import com.example.typeformclone.model.UserPurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPurchasedProductRepository extends JpaRepository<UserPurchasedProduct, Long> {
    List<UserPurchasedProduct> findByUserId(Long userId);
}
