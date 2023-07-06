package com.example.project4.model;

public class CategoryModel {
    private String CatName;

    public CategoryModel() {
        // Default constructor required for Firebase
    }

    public CategoryModel(String catName) {
        this.CatName = catName;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        this.CatName = catName;
    }
}
