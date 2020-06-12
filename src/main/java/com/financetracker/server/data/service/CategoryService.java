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

    private static final String categoryNotExistError = "Category does not exist";
    private static final String categoryCreateError = "Create category failed";
    private static final String categoriesForUserError = "Retrieving categories failed";

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public void addCategoryToUser(Category category) {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());

        if(user != null){
            category.setUser(user);
            categoryRepository.save(category);
        } else{
            throw new CategoryException(categoryCreateError);
        }
    }

    public void updateCategory(long id, Category category)throws CategoryException {
        List<Category> categories= categoryRepository.findById(id);

        if(categories != null && !categories.isEmpty()){
            Category categoryDB = categories.get(0);
            categoryDB.setBudget(category.getBudget());
            categoryDB.setDescription(category.getDescription());
            categoryDB.setName(category.getName());
            categoryRepository.save(categoryDB);
        } else {
            throw new CategoryException(categoryNotExistError);
        }
    }

    public List<Category> getAllCategoriesForUser() {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());

        if(user != null){
            return categoryRepository.findByUser(user);
        } else{
            throw new CategoryException(categoriesForUserError);
        }
    }

    public Category getCategory(long id){
        List<Category> categories = categoryRepository.findById(id);

        if(categories != null && !categories.isEmpty()){
            return categories.get(0);
        } else {
            throw new CategoryException(categoryNotExistError);
        }
    }

    public void destroyCategory(long id){
        List<Category> categories = categoryRepository.findById(id);

        if(categories != null && !categories.isEmpty()){
            Category categoryDB = categories.get(0);
            categoryRepository.delete(categoryDB);
        }
    }
}
