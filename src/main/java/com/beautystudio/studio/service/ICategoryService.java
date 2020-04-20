package com.beautystudio.studio.service;

import com.beautystudio.studio.model.Category;

import java.util.List;

public interface ICategoryService {
    void add(Category category);
    List<Category> findAll();
    Category getById(Long id);
}
