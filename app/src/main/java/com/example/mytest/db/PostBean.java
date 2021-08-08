package com.example.mytest.db;

import java.util.List;

public class PostBean {
    /*
    * 发帖类
    * */
    String userid;//发帖人id
    String postid;//帖子id，方便存储评论
    String username;//发帖人昵称
    int head_image;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    String words;//文字内容
    List<Integer> images;
    int agrees;//点赞数

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHead_image() {
        return head_image;
    }

    public void setHead_image(int head_image) {
        this.head_image = head_image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public int getAgrees() {
        return agrees;
    }

    public void setAgrees(int agrees) {
        this.agrees = agrees;
    }

    public PostBean(String username, int head_image, int hour, int minute, String words, List<Integer> images) {
        this.username = username;
        this.head_image = head_image;
        this.hour = hour;
        this.minute = minute;
        this.words = words;
        this.images = images;
    }
}
