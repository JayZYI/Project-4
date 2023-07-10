package com.example.adminandroid.Model;

import com.google.firebase.database.PropertyName;

public class tag {
    private String categoryId;

    @PropertyName("catName")
    private String catName;

    // Constructor
    public tag() {
    }

    // Getter and Setter methods
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getcatName() {
        return catName;
    }

    public void setcatName(String catName) {
        this.catName = catName;
    }
}
