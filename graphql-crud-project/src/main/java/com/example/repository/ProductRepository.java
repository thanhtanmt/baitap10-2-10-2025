package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long userId);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllOrderedByPriceAsc();

    @Query("SELECT p FROM Product p JOIN p.user u JOIN u.categories c WHERE c.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
}