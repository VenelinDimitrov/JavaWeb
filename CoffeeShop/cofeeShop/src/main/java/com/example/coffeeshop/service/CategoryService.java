package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {

    Category findByName(CategoryNameEnum name);
}
