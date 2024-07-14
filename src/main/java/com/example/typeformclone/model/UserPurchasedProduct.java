package com.example.typeformclone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_purchased_products")
public class UserPurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
