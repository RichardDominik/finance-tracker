package com.financetracker.server.data.service;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.repository.CategoryRepository;
import com.financetracker.server.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    public void addCategoryToUser(Category category, String uid){
        List<User> users = userRepository.findByUid(uid);
        if(users != null && !users.isEmpty()){
            category.setUser(users.get(0));
            categoryRepository.save(category);
        } else{
            //todo err message to client
        }
    }

    public void updateCategory(Category category){
        List<Category> categories= categoryRepository.findByUid(category.getUid());
        if(categories != null && !categories.isEmpty()){
            Category categoryDB = categories.get(0);
            categoryDB.setBudget(category.getBudget());
            categoryDB.setDescription(category.getDescription());
            categoryDB.setName(category.getName());

            categoryRepository.save(categoryDB);
        }
    }

    public List<Category> getAllCategoriesForUser(String  uid){
        List<User> users = userRepository.findByUid(uid);
        if(users != null && !users.isEmpty()){
            return categoryRepository.findByUser(users.get(0));
        } else{
            //todo
            return null;
        }
    }

    public void destroyCategory(String uid){
        List<Category> categories = categoryRepository.findByUid(uid);
        if(categories != null && !categories.isEmpty()){
            Category categoryDB = categories.get(0);
            categoryRepository.delete(categoryDB);
        }
    }
}
