package com.example.dto;

import lombok.Data;

@Data
public class CreateProductInput {
    private String title;
    private int quantity;
    private String desc;
    private double price;
    private Long userId;
}