package com.example.andreys.service.impl;

import com.example.andreys.model.entity.Product;
import com.example.andreys.model.service.AddProductServiceModel;
import com.example.andreys.repository.ProductRepository;
import com.example.andreys.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddProductServiceModel addProduct(AddProductServiceModel addProductServiceModel) {
        Product product = modelMapper.map(addProductServiceModel, Product.class);

        productRepository.save(product);

        return modelMapper.map(product, AddProductServiceModel.class);
    }

    @Override
    public Set<Product> getAllProducts() {

        return productRepository.findAllProducts();
    }

    @Override
    public Product findProductByName(String name) {

        return productRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public void deleteProductByName(String name) {
        productRepository.deleteByName(name);
    }
}
