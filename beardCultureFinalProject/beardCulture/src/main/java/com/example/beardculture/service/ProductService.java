package com.example.beardculture.service;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.model.service.AddProductServiceModel;

public interface ProductService {
    AddProductServiceModel addProduct(AddProductBindingModel addProductBindingModel);
}