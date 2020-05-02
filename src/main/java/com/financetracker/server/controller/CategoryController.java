package com.financetracker.server.controller;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.exception.CategoryException;
import com.financetracker.server.data.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody Category category){

        categoryService.addCategoryToUser(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userCategories")
    public List<Category> getCategoriesForUser(){
        return categoryService.getAllCategoriesForUser();
    }

    @PostMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try{
            categoryService.updateCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CategoryException e){
            LOGGER.error("Update category failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Updating cateogry failed", HttpStatus.BAD_REQUEST);
    }
}
