package com.example.graphql;

import com.example.dto.*;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        User user = new User();
        user.setFullname(input.getFullname());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setPhone(input.getPhone());
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        User user = userRepository.findById(input.getId()).orElseThrow();
        if (input.getFullname() != null) user.setFullname(input.getFullname());
        if (input.getEmail() != null) user.setEmail(input.getEmail());
        if (input.getPassword() != null) user.setPassword(input.getPassword());
        if (input.getPhone() != null) user.setPhone(input.getPhone());
        return userRepository.save(user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tương tự cho Category
    @MutationMapping
    public Category createCategory(@Argument CreateCategoryInput input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setImages(input.getImages());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category updateCategory(@Argument UpdateCategoryInput input) {
        Category category = categoryRepository.findById(input.getId()).orElseThrow();
        if (input.getName() != null) category.setName(input.getName());
        if (input.getImages() != null) category.setImages(input.getImages());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tương tự cho Product
    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        Product product = new Product();
        product.setTitle(input.getTitle());
        product.setQuantity(input.getQuantity());
        product.setDesc(input.getDesc());
        product.setPrice(input.getPrice());
        User user = userRepository.findById(input.getUserId()).orElseThrow();
        product.setUser(user);
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument UpdateProductInput input) {
        Product product = productRepository.findById(input.getId()).orElseThrow();
        if (input.getTitle() != null) product.setTitle(input.getTitle());
        if (input.getQuantity() != 0) product.setQuantity(input.getQuantity());
        if (input.getDesc() != null) product.setDesc(input.getDesc());
        if (input.getPrice() != 0.0) product.setPrice(input.getPrice());
        if (input.getUserId() != null) {
            User user = userRepository.findById(input.getUserId()).orElseThrow();
            product.setUser(user);
        }
        return productRepository.save(product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}