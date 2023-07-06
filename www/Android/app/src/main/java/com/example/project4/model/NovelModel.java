package com.example.project4.model;

public class NovelModel {
    private String novelCover;
    private int chapters;
    private int readTimes;
    private String tag;
    private String title;
    private int views;

    public NovelModel() {
        // Default constructor required for Firebase
    }

    public String getNovelCover() {
        return novelCover;
    }

    public void setNovelCover(String novelCover) {
        this.novelCover = novelCover;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
