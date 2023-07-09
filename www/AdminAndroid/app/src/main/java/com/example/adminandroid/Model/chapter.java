package com.example.adminandroid.Model;

public class chapter {
        private String chapId;
        private String chapTitle;
        private String content;
        private String novel;

        // Constructor
        public chapter() {
        }

        // Getter and Setter methods
        public String getChapId() {
            return chapId;
        }

        public void setChapId(String chapId) {
            this.chapId = chapId;
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
