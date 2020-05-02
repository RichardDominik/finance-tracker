package com.financetracker.server.data.service;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.exception.CategoryException;
import com.financetracker.server.data.repository.CategoryRepository;
import com.financetracker.server.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public void addCategoryToUser(Category category) {
        User user = userService.loadUserByUsername(userService.getPrincipalUsername());
        if(user != null){
            category.setUser(user);
            categoryRepository.save(category);
        } else{
            //todo err message to client
        }
    }

    public void updateCategory(Category category)throws CategoryException {
        List<Category> categories= categoryRepository.findById(category.getId());
        if(categories != null && !categories.isEmpty()){
            Category categoryDB = categories.get(0);
            categoryDB.setBudget(category.getBudget());
            categoryDB.setDescription(category.getDescription());
            categoryDB.setName(category.getName());
            categoryRepository.save(categoryDB);
        } else {
            throw new CategoryException("Category does not exist");
        }
    }

    public List<Category> getAllCategoriesForUser() {
        User user = userService.loadUserByUsername(userService.getPrincipalUsername());
        if(user != null){
            return categoryRepository.findByUser(user);
        } else{
            //todo
            return null;
        }
    }
}
