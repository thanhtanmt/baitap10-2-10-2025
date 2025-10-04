package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int quantity;
    private String desc;
    private double price;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}