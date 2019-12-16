package com.netcracker.edu.fapi.models.viewModels;


import java.util.Date;
import java.util.List;

public class ViewPoll {
    private Integer id;
    private String name;
    private Integer idUser;
    private String theme;
    private String description;
    private String link;
    private Date date;
    private String status;
    private String shared;
    private List<ViewQuestion> questions;

    public ViewPoll() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public List<ViewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ViewQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ViewPoll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idUser=" + idUser +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", shared='" + shared + '\'' +
                ", questions=" + questions +
                '}';
    }
}
