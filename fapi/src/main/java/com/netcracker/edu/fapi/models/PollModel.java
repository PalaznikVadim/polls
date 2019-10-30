package com.netcracker.edu.fapi.models;

import java.util.Date;

public class PollModel {
    private int id;
    private String title;
    private String theme;
    private Date dateAdding;
    private String description;


    public PollModel() {
    }

    public PollModel(int id, String title, String theme, Date dateAdding, String description) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.dateAdding = dateAdding;
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
