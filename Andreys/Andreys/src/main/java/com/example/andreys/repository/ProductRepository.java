package com.example.andreys.repository;

import com.example.andreys.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    Set<Product> findAllProducts();

    Optional<Product> findByName(String name);

    void deleteByName(String name);
}
