package com.example.andreys.model.service;

import com.example.andreys.model.entity.enums.CategoryEnum;
import com.example.andreys.model.entity.enums.SexEnum;

import java.math.BigDecimal;

public class ProductDetailsServiceModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnum category;
    private SexEnum sex;

    public ProductDetailsServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
