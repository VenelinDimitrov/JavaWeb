package com.example.beardculture.service.impl;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.Manufacturer;
import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;
import com.example.beardculture.model.service.AddProductServiceModel;
import com.example.beardculture.model.service.ProductUpdateServiceModel;
import com.example.beardculture.repository.ProductRepository;
import com.example.beardculture.service.CategoryService;
import com.example.beardculture.service.ManufacturerService;
import com.example.beardculture.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

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

        Category category = categoryService.getCategoryByName(addProductBindingModel.getCategory());

        product.setManufacturer(manufacturer);
        product.setCategory(category);

        if (addProductBindingModel.getCategory() == CategoryNameEnum.OIL) {
            product.setImageUrl("/images/oils/" + addProductBindingModel.getImageUrl() + ".jpg");
        } else if (addProductBindingModel.getCategory() == CategoryNameEnum.BALM) {
            product.setImageUrl("/images/balms/" + addProductBindingModel.getImageUrl() + ".jpg");
        } else {
            product.setImageUrl("/images/gear/" + addProductBindingModel.getImageUrl() + ".jpg");
        }

        productRepository.save(product);

        return new AddProductServiceModel();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllOils() {
        Category oilCategory = categoryService.getCategoryByName(CategoryNameEnum.OIL);

        return productRepository.findAllByCategory(oilCategory);
    }

    @Override
    public List<Product> getAllBalms() {
        Category balmCategory = categoryService.getCategoryByName(CategoryNameEnum.BALM);

        return productRepository.findAllByCategory(balmCategory);
    }

    @Override
    public List<Product> getAllGear() {
        Category gearCategory = categoryService.getCategoryByName(CategoryNameEnum.GEAR);

        return productRepository.findAllByCategory(gearCategory);
    }

    @Override
    public void updateProduct(ProductUpdateServiceModel productUpdateServiceModel) {
        Product product = productRepository.findById(productUpdateServiceModel.getId()).orElse(null);

        if (product != null){
            product.setQuantity(productUpdateServiceModel.getQuantity());
            product.setPrice(productUpdateServiceModel.getPrice());
            product.setImageUrl(productUpdateServiceModel.getImageUrl());

            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllBy();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
