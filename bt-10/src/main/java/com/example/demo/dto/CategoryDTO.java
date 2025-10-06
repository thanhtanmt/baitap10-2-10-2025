package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(min = 3, max = 50, message = "Tên danh mục phải từ 3-50 ký tự")
    private String name;
}