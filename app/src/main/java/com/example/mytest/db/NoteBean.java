package com.example.mytest.db;

public class NoteBean {
    private String id;//用户id
    private String title;
    private String content;
    private int month;
    private int day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public NoteBean(String id, String title, String content, int month, int day) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.month = month;
        this.day = day;
    }
}
