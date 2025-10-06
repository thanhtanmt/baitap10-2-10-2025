package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "admin/add-category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("categoryDTO") CategoryDTO categoryDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/add-category";
        }
        categoryService.saveCategory(categoryDTO);
        return "redirect:/admin/home";
    }
}