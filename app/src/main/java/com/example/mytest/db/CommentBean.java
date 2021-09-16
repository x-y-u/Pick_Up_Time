package com.example.mytest.db;

public class CommentBean {
    int head_image;
    String username;
    String content;
    String time;

    public int getHead_image() {
        return head_image;
    }

    public void setHead_image(int head_image) {
        this.head_image = head_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CommentBean(int head_image, String username, String content, String time) {
        this.head_image = head_image;
        this.username = username;
        this.content = content;
        this.time = time;
    }
}
