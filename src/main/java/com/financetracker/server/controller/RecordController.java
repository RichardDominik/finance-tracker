package com.financetracker.server.controller;

import com.financetracker.server.controller.request.CreateRecordRequest;
import com.financetracker.server.controller.response.DefaultResponse;
import com.financetracker.server.data.entity.Record;
import com.financetracker.server.data.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    RecordService recordService;

    @PostMapping("/record/create")
    public DefaultResponse createRecord(@Valid @RequestBody CreateRecordRequest request){
        recordService.createNewRecord(request);
        return new DefaultResponse(null, "OK");
    }

    @GetMapping("/records")
    public List<Record> getAllRecords(@RequestParam("userId") String userId, @RequestParam("categoryId") String categoryId){
        return recordService.getAllRecordsForCategoryAndUser(userId, categoryId);
    }
}
