package com.example.typeformclone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GiftCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int value; // The value of the gift card in points

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
