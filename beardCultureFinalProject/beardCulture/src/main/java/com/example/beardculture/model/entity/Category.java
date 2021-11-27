package com.example.beardculture.model.entity;

import com.example.beardculture.model.entity.enums.CategoryNameEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private CategoryNameEnum name;

    public Category() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }
}
