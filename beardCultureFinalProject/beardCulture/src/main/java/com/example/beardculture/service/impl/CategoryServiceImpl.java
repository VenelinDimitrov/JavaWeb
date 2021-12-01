package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;
import com.example.beardculture.repository.CategoryRepository;
import com.example.beardculture.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryByName(CategoryNameEnum name) {
        return categoryRepository.findCategoryByName(name).orElse(null);
    }
}
