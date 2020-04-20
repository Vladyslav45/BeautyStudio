package com.beautystudio.studio.controller;

import com.beautystudio.studio.model.Category;
import com.beautystudio.studio.service.ICategoryService;
import com.beautystudio.studio.service.ISubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ServiceController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private ISubCategoryService iSubCategoryService;

    @GetMapping(value = "/category")
    public String showAllCategory(Model model){
        List<Category> categories = iCategoryService.findAll();
        model.addAttribute("categoriesList", categories);
        return "index";
    }
}
