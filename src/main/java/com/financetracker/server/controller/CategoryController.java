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
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private static final String updateError = "Update category failed";

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){
        categoryService.addCategoryToUser(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user-categories")
    public List<Category> getCategoriesForUser(){
        return categoryService.getAllCategoriesForUser();
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody Category category) {
        try {
            categoryService.updateCategory(id, category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CategoryException e){
            LOGGER.error(updateError + " " + e.getMessage());
        }
        return new ResponseEntity<>(updateError, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/category/{id}/show")
    public Category findOne(@PathVariable long id){
        return categoryService.getCategory(id);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> destroyCategory(@PathVariable long id){
        categoryService.destroyCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
