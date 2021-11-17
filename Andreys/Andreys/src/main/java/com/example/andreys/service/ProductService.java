package com.example.andreys.service;

import com.example.andreys.model.entity.Product;
import com.example.andreys.model.service.AddProductServiceModel;

import java.util.Set;

public interface ProductService {
    AddProductServiceModel addProduct(AddProductServiceModel addProductServiceModel);

    Set<Product> getAllProducts();

    Product findProductByName(String name);

    void deleteProductByName(String name);
}
