package com.example.adminandroid.Model;

public class novel {
    private String novelId;
    private String chapters;
    private String novelCover;
    private String readTimes;
    private String tag;
    private String title;
    private String views;

    // Constructor
    public novel() {
    }

    // Getter and Setter methods
    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    public String getNovelCover() {
        return novelCover;
    }

    public void setNovelCover(String novelCover) {
        this.novelCover = novelCover;
    }

    public String getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(String readTimes) {
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
