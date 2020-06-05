package com.financetracker.server.controller;

import com.financetracker.server.controller.request.CreateRecordRequest;
import com.financetracker.server.data.exception.CategoryException;
import com.financetracker.server.data.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class RecordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    RecordService recordService;

    @PostMapping("/record/create")
    public ResponseEntity<?> createRecord(@Valid @RequestBody CreateRecordRequest request) {
        try{
            recordService.createNewRecord(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CategoryException e){
            LOGGER.error("Create records failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Creating record failed", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/record/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable long id, @Valid @RequestBody CreateRecordRequest request) {
        try {
            recordService.updateRecord(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CategoryException e){
            LOGGER.error("Update record failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Updating record failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/records")
    public ResponseEntity<?> getAllRecords(@RequestParam("categoryId") long categoryId) {
        try{
            return new ResponseEntity<>(recordService.getAllRecordsForCategoryAndUser(categoryId), HttpStatus.OK);
        } catch (CategoryException e){
            LOGGER.error("Get all records failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Retrieving records failed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity<?> destroyRecord(@PathVariable long id){
        recordService.destroyRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
