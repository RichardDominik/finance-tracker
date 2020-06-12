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

    private static final String categoryNotExistError = "Category does not exist";
    private static final String recordNotExistError = "Record does not exist";

    @Autowired
    RecordRepository recordRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;

    public void createNewRecord(CreateRecordRequest req) throws CategoryException{
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());
        Category category = getCategory(req.getCategoryId());

        Record rec = req.getRecord();
        rec.setUser(user);
        rec.setCategory(category);
        recordRepository.save(rec);
    }

    public void updateRecord(long id, CreateRecordRequest req) throws CategoryException {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());
        Category category = getCategory(req.getCategoryId());
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
            throw new CategoryException(recordNotExistError);
        }
    }

    public List<Record> getAllRecordsForCategoryAndUser(long categoryId) throws CategoryException {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());
        Category category = getCategory(categoryId);

        return recordRepository.findByUserAndCategory(user, category);
    }

    public List<Record> getAllRecordsForUser() {
        User user = userService.loadUserByEmail(userService.getPrincipalEmail());
        return recordRepository.findByUser(user);
    }

    public void destroyRecord(long id){
        List<Record> records = recordRepository.findById(id);

        if(records != null && !records.isEmpty()){
            Record recordDB = records.get(0);
            recordRepository.delete(recordDB);
        }
    }

    private Category getCategory(long categoryId) throws CategoryException {
        List<Category> categories = categoryRepository.findById(categoryId);

        if(categories == null || categories.isEmpty()){
            throw new CategoryException(categoryNotExistError);
        }

        return categories.get(0);
    }
}
