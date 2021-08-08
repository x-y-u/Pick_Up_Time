package com.example.mytest.db;

public class UserBean {
    String id;
    String username;
    String password;
    int sex;//性别：男--1，女--0
    String phonenum;
    int score;//积分
    int year;
    int month;
    int day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public UserBean(String id, String username, String password, int sex, String phonenum, int score, int year, int month, int day) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phonenum = phonenum;
        this.score = score;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
