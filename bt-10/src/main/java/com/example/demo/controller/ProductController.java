package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories()); // Để chọn category
        return "admin/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/add-product";
        }
        productService.saveProduct(productDTO);
        return "redirect:/admin/home";
    }
}