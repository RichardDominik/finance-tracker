package com.financetracker.server.controller.request;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.Record;

import java.io.Serializable;

public class CreateRecordRequest implements Serializable {

    private Category category;
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }
}
