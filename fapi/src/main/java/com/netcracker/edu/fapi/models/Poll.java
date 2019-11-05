package com.netcracker.edu.fapi.models;

import java.util.Date;

public class Poll {
    private int id;
    private String name;
    private int idUser;
    private int idTheme;
    private String description;
    private String link;
    private Date date;
    private String status;
    private String shared;

    public Poll() {
    }

    public Poll(int id, String name, int idUser, int idTheme, String description, String link, Date date, String status, String shared) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
        this.idTheme = idTheme;
        this.description = description;
        this.link = link;
        this.date = date;
        this.status = status;
        this.shared = shared;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
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

    public Date getDataTime() {
        return date;
    }

    public void setDataTime(Date data) {
        this.date = data;
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

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idUser=" + idUser +
                ", idTheme=" + idTheme +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", shared='" + shared + '\'' +
                '}';
    }
}
