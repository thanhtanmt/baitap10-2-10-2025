package com.example.dto;

import lombok.Data;

@Data
public class UpdateUserInput {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String phone;
}