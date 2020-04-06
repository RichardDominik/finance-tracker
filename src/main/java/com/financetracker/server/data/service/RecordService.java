package com.financetracker.server.data.service;

import com.financetracker.server.controller.request.CreateRecordRequest;
import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.Record;
import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.repository.CategoryRepository;
import com.financetracker.server.data.repository.RecordRepository;
import com.financetracker.server.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public void createNewRecord(CreateRecordRequest req){

        Optional<User> user = userRepository.findById(req.getUserId());
        if(user.isEmpty()){
            //todo errror response
        }

        Optional<Category> category = categoryRepository.findById(req.getCategoryId());
        if(category.isEmpty()){
            //todo
        }

        Record rec = req.getRecord();
        rec.setUser(user.get());
        rec.setCategory(category.get());
        recordRepository.save(rec);
    }

    public List<Record> getAllRecordsForCategoryAndUser(String userId, String categoryId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            //todo errror response
        }

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            //todo
        }
        return recordRepository.findByUserAndCategory(user.get(), category.get());
    }
}
