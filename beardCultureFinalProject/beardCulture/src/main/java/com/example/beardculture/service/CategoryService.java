package com.example.beardculture.service;

import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    Category getCategoryByName(CategoryNameEnum name);
}
