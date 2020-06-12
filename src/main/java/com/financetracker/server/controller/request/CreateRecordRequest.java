package com.financetracker.server.controller.request;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.Record;
import java.io.Serializable;

public class CreateRecordRequest implements Serializable {

    private long categoryId;
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
