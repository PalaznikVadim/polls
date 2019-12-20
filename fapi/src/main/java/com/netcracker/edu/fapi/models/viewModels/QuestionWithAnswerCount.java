package com.netcracker.edu.fapi.models.viewModels;

public class QuestionWithAnswerCount {
    private int id;
    private String title;
    private long count;

    public QuestionWithAnswerCount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}