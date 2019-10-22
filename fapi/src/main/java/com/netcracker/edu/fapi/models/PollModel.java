package com.netcracker.edu.fapi.models;

import java.util.Date;

public class PollModel {
    private int id;
    private String title;
    private Date dateAdding;

    public PollModel() {
    }

    public PollModel(int id, String title, Date dateAdding) {
        this.id = id;
        this.title = title;
        this.dateAdding = dateAdding;
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

    public Date getDateAdding() {
        return dateAdding;
    }

    public void setDateAdding(Date dateAdding) {
        this.dateAdding = dateAdding;
    }
}
