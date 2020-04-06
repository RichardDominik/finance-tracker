package com.financetracker.server.controller.request;

import com.financetracker.server.data.entity.Category;

import java.io.Serializable;

public class CreateCategoryRequest implements Serializable {

    private String userId;
    private Category category;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
