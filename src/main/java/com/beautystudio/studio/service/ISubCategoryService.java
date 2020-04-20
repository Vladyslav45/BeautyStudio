package com.beautystudio.studio.service;

import com.beautystudio.studio.model.SubCategory;

import java.util.List;

public interface ISubCategoryService {
    void add(SubCategory subCategory);
    SubCategory findById(Long id);
    List<SubCategory> findAll();
    List<SubCategory> findAllByCategodyId(Long categoryId);
}
