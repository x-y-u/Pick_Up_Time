package com.example.mytest.db;

import java.util.List;

public class MemoryBean {
    String userid;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    String words;//文字内容
    List<Integer> images;
    String weekday;//周几

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

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public MemoryBean(String userid, int year, int month, int day, int hour, int minute, String words, List<Integer> images, String weekday) {
        this.userid = userid;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.words = words;
        this.images = images;
        this.weekday = weekday;
    }
}
