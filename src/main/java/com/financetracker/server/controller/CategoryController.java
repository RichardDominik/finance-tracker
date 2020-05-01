package com.financetracker.server.controller;

import com.financetracker.server.controller.request.CreateCategoryRequest;
import com.financetracker.server.controller.response.DefaultResponse;
import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/create")
    public DefaultResponse createCategory(@RequestBody CreateCategoryRequest req){
        categoryService.addCategoryToUser(req.getCategory(), req.getUserId());
        return new DefaultResponse(null, "OK");
    }

    @GetMapping("/user-categories")
    public List<Category> getCategoriesForUser(@RequestParam("id") String uid){
        return categoryService.getAllCategoriesForUser(uid);
    }

    @PostMapping("/category/update")
    public DefaultResponse updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return new DefaultResponse(null, "OK");
    }
}
