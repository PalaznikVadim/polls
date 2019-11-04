package com.netcracker.edu.fapi.models;

import java.util.Collection;
import java.util.Date;

public class Poll {
    private int id;
    private String title;
    private String description;
    private String link;
    private Date date;
    private String status;
    private String shared;
    private User user;
    private Theme theme;
    private Collection<Question> question;

    public Poll() {
    }

    public Poll(int id, String title, String description, String link, Date date, String status, String shared, User user, Theme theme, Collection<Question> question) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.date = date;
        this.status = status;
        this.shared = shared;
        this.user = user;
        this.theme = theme;
        this.question = question;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDateAdding() {
        return date;
    }

    public void setDateAdding(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Collection<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Collection<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "PollModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", dateAdding=" + date +
                ", status='" + status + '\'' +
                ", shared='" + shared + '\'' +
                ", user=" + user +
                ", theme=" + theme +
                ", question=" + question +
                '}';
    }
}
