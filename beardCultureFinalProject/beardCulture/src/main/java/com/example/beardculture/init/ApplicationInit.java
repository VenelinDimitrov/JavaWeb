package com.example.beardculture.init;

import com.example.beardculture.model.entity.Category;
import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.repository.CategoryRepository;
import com.example.beardculture.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationInit implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;
    private final CategoryRepository categoryRepository;

    public ApplicationInit(UserRoleRepository userRoleRepository, CategoryRepository categoryRepository) {
        this.userRoleRepository = userRoleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            Arrays.stream(RoleNameEnum.values())
                    .forEach(roleNameEnum -> {
                        Role role = new Role();
                        role.setRole(roleNameEnum);

                        userRoleRepository.save(role);
                    });
        }

        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        Category category = new Category();
                        category.setName(categoryNameEnum);

                        categoryRepository.save(category);
                    });
        }
    }
}
