package com.example.beardculture.service.impl;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.model.entity.Manufacturer;
import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.service.AddProductServiceModel;
import com.example.beardculture.repository.ProductRepository;
import com.example.beardculture.service.CategoryService;
import com.example.beardculture.service.ManufacturerService;
import com.example.beardculture.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public AddProductServiceModel addProduct(@Valid AddProductBindingModel addProductBindingModel) {
        Product product = modelMapper.map(addProductBindingModel, Product.class);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(addProductBindingModel.getManufacturer());

        manufacturerService.addManufacturer(manufacturer);

        product.setManufacturer(manufacturer);


        // TODO: Finish implementing the Add product function

        return new AddProductServiceModel();
    }
}
