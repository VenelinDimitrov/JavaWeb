package com.example.beardculture.service;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.service.AddProductServiceModel;

import java.util.List;

public interface ProductService {
    AddProductServiceModel addProduct(AddProductBindingModel addProductBindingModel);

    Product getProductById(Long id);

    List<Product> getAllOils();

    List<Product> getAllBalms();

    List<Product> getAllGear();
}
