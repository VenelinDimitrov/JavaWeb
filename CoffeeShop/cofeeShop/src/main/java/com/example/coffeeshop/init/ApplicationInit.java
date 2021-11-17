package com.example.coffeeshop.init;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public ApplicationInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        Category category = new Category();
                        category.setName(categoryNameEnum);
                        switch (categoryNameEnum){
                            case DRINK:category.setNeededTime(1);
                            break;
                            case CAKE:category.setNeededTime(10);
                            break;
                            case COFFEE:category.setNeededTime(2);
                            break;
                            case OTHER:category.setNeededTime(5);
                            break;
                        }

                        categoryRepository.save(category);
                    });
        }
    }
}
