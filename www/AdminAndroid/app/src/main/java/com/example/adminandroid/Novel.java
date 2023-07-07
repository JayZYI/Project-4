package com.example.adminandroid;

import android.os.Parcel;
import android.os.Parcelable;

public class Novel implements Parcelable {
    private String id;
    private String chapters;
    private String novelCover;
    private String readTimes;
    private String tag;
    private String title;
    private String views;

    public Novel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    // Implement the Parcelable interface methods here
    // ...

    protected Novel(Parcel in) {
        id = in.readString();
        chapters = in.readString();
        novelCover = in.readString();
        readTimes = in.readString();
        tag = in.readString();
        title = in.readString();
        views = in.readString();
    }

    public static final Creator<Novel> CREATOR = new Creator<Novel>() {
        @Override
        public Novel createFromParcel(Parcel in) {
            return new Novel(in);
        }

        @Override
        public Novel[] newArray(int size) {
            return new Novel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(chapters);
        dest.writeString(novelCover);
        dest.writeString(readTimes);
        dest.writeString(tag);
        dest.writeString(title);
        dest.writeString(views);
    }
}
