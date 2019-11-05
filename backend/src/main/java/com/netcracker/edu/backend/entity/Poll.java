package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.entity.enums.Shared;
import com.netcracker.edu.backend.entity.enums.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
public class Poll {
    private int id;
    private String name;
    private int idUser;
    private int idTheme;
    private String description;
    private String link;
    private Date date;
    private Status status;
    private Shared shared;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_theme")
    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "data_time")
    public Date getDataTime() {
        return date;
    }

    public void setDataTime(Date dataTime) {
        this.date = dataTime;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "shared")
    public Shared getShared() {
        return shared;
    }

    public void setShared(Shared shared) {
        this.shared = shared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return id == poll.id &&
                idUser == poll.idUser &&
                idTheme == poll.idTheme &&
                Objects.equals(name, poll.name) &&
                Objects.equals(description, poll.description) &&
                Objects.equals(link, poll.link) &&
                Objects.equals(date, poll.date) &&
                Objects.equals(status, poll.status) &&
                Objects.equals(shared, poll.shared);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idUser, idTheme, description, link, date, status, shared);
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
                ", status=" + status +
                ", shared=" + shared +
                '}';
    }
}
