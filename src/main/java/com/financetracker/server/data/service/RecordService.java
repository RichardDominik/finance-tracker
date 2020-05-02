package com.financetracker.server.data.service;

import com.financetracker.server.controller.request.CreateRecordRequest;
import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.Record;
import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.exception.CategoryException;
import com.financetracker.server.data.repository.CategoryRepository;
import com.financetracker.server.data.repository.RecordRepository;
import com.financetracker.server.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;

    public void createNewRecord(CreateRecordRequest req){

        User user = userService.loadUserByUsername(userService.getPrincipalUsername());

        Category category = categoryRepository.findById(req.getCategory().getId()).get(0);
        if(category == null){
            throw new CategoryException("Kategória neexistuje");
        }

        Record rec = req.getRecord();
        rec.setUser(user);
        rec.setCategory(category);
        recordRepository.save(rec);
    }

    public List<Record> getAllRecordsForCategoryAndUser(long categoryId)throws CategoryException{
        User user = userService.loadUserByUsername(userService.getPrincipalUsername());

        Category category = categoryRepository.findById(categoryId).get(0);
        if(category == null){
            throw new CategoryException("Kategória neexistuje");
        }
        return recordRepository.findByUserAndCategory(user, category);
    }
}
