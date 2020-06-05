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

    public void createNewRecord(CreateRecordRequest req) {

        User user = userService.loadUserByEmail(userService.getPrincipalEmail());

        Category category = categoryRepository.findById(req.getCategoryId()).get(0);
        if(category == null){
            throw new CategoryException("Category does not exist");
        }

        Record rec = req.getRecord();
        rec.setUser(user);
        rec.setCategory(category);
        recordRepository.save(rec);
    }

    public void updateRecord(long id, CreateRecordRequest req) throws CategoryException {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());
        Category category = categoryRepository.findById(req.getCategoryId()).get(0);

        if(category == null){
            throw new CategoryException("Category does not exist");
        }

        List<Record> records = recordRepository.findById(id);
        if(records != null && !records.isEmpty()){
            Record recordDB = records.get(0);
            recordDB.setType(req.getRecord().getType());
            recordDB.setDescription(req.getRecord().getDescription());
            recordDB.setAmount(req.getRecord().getAmount());
            recordDB.setUser(user);
            recordDB.setCategory(category);
            recordRepository.save(recordDB);
        } else {
            throw new CategoryException("Record does not exist");
        }
    }

    public List<Record> getAllRecordsForCategoryAndUser(long categoryId) throws CategoryException {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());

        Category category = categoryRepository.findById(categoryId).get(0);
        if(category == null){
            throw new CategoryException("Category does not exist");
        }
        return recordRepository.findByUserAndCategory(user, category);
    }

    public void destroyRecord(long id){
        List<Record> records = recordRepository.findById(id);

        if(records != null && !records.isEmpty()){
            Record recordDB = records.get(0);
            recordRepository.delete(recordDB);
        }
    }
}
