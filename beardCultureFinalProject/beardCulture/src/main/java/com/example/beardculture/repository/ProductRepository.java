package com.example.beardculture.repository;

import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    List<Product> getAllBy();
    Optional<Product> getByName(String name);
}
