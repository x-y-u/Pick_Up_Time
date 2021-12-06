package com.example.mytest.db;

import java.io.Serializable;
import java.util.List;

public class MemoryBean implements Serializable {
    String userid;
    int year;
    int month;
    int day;
    String title;
    String words;//文字内容
    //List<Integer> images;
    String imageurl;
    String weekday;//周几
    int state = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

//    public List<Integer> getImages() {
//        return images;
//    }
//
//    public void setImages(List<Integer> images) {
//        this.images = images;
//    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MemoryBean(String userid, int year, int month, int day, String title,String words, String imageurl, String weekday) {
        this.userid = userid;
        this.year = year;
        this.month = month;
        this.day = day;
        this.words = words;
        this.title = title;
        //this.images = images;
        this.imageurl = imageurl;
        this.weekday = weekday;
    }
}
