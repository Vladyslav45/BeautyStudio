package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.model.SubCategory;
import com.beautystudio.studio.repository.SubCategoryRepository;
import com.beautystudio.studio.service.ISubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements ISubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public void add(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory findById(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<SubCategory> findAllByCategodyId(Long categoryId) {
        return subCategoryRepository.findAllByCategoryId(categoryId);
    }
}
