package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;
import com.example.beardculture.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    private Category testCategory;

    private CategoryServiceImpl serviceToTest;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @BeforeEach
    public void init(){
        serviceToTest = new CategoryServiceImpl(mockCategoryRepository);

        testCategory = new Category();
        testCategory.setName(CategoryNameEnum.BALM);
    }

    @Test
    public void testCategoryFoundByName(){
        Mockito.when(mockCategoryRepository.findCategoryByName(testCategory.getName()))
                .thenReturn(Optional.of(testCategory));

        var actual = serviceToTest.getCategoryByName(testCategory.getName());

        Assertions.assertEquals(actual.getName(), testCategory.getName());
    }
}
