package com.example.mytest.db;

import java.util.List;

public class PostBean {
    /*
    * 发帖类
    * */
    int postid;//帖子id，方便存储评论
    String username;//发帖人昵称
    String head_image;
    String time;
    String words;//文字内容
    String first_img;
    int agrees;//点赞数
    int comments;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getAgrees() {
        return agrees;
    }

    public void setAgrees(int agrees) {
        this.agrees = agrees;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFirst_img() {
        return first_img;
    }

    public void setFirst_img(String first_img) {
        this.first_img = first_img;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public PostBean(int postid, String username, String head_image, String time, String words, String first_img, int agrees, int comments) {
        this.postid = postid;
        this.username = username;
        this.head_image = head_image;
        this.time = time;
        this.words = words;
        this.first_img = first_img;
        this.agrees = agrees;
        this.comments = comments;
    }
}
