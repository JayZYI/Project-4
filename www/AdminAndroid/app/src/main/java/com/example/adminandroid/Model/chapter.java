package com.example.adminandroid.Model;

public class chapter {
    private String Id;
    private String chapTitle;
    private String content;
    private String novel;

    public chapter() {
        // Default constructor required for Firebase
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getChapTitle() {
        return chapTitle;
    }

    public void setChapTitle(String chapTitle) {
        this.chapTitle = chapTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNovel() {
        return novel;
    }

    public void setNovel(String novel) {
        this.novel = novel;
    }
}
