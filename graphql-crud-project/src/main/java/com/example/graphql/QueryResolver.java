package com.example.graphql;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @QueryMapping
    public List<Product> allProductsByPrice() {
        return productRepository.findAllOrderedByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }
}