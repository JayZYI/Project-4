package com.example.project4;

public class modelNovel {
        private String id;
        private String title;
        private String tag;
        private String novelCover;
        private int chapters;
        private int views;
        private int viewTimes;

        // Setter methods
        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setNovelCover(String novelCover) {
            this.novelCover = novelCover;
        }

        public void setChapters(int chapters) {
            this.chapters = chapters;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public void setViewTimes(int viewTimes) {
            this.viewTimes = viewTimes;
        }

        // Getter methods
        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getNovelCover() {
            return novelCover;
        }

        public int getChapters() {
            return chapters;
        }

        public int getViews() {
            return views;
        }

        public int getViewTimes() {
            return viewTimes;
        }
}
