package com.financetracker.server.controller.request;

import com.financetracker.server.data.entity.Record;

import java.io.Serializable;

public class CreateRecordRequest implements Serializable {

    private String categoryId;
    private String userId;
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
