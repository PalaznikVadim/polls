package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.entity.enums.Shared;
import com.netcracker.edu.backend.entity.enums.Status;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Poll {
    private int id;
    private String title;
    private String description;
    private String link;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Shared shared;
    private User userByIdUser;
    private Theme themeByIdTheme;
    private Collection<Question> questionsById;

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
        return title;
    }

    public void setName(String title) {
        this.title = title;
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

    public void setDataTime(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
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
                Objects.equals(title, poll.title) &&
                Objects.equals(description, poll.description) &&
                Objects.equals(link, poll.link) &&
                Objects.equals(date, poll.date) &&
                Objects.equals(status, poll.status) &&
                Objects.equals(shared, poll.shared);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, link, date, status, shared);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_theme", referencedColumnName = "id", nullable = false)
    public Theme getThemeByIdTheme() {
        return themeByIdTheme;
    }

    public void setThemeByIdTheme(Theme themeByIdTheme) {
        this.themeByIdTheme = themeByIdTheme;
    }

    @OneToMany(mappedBy = "pollByIdPoll")
    public Collection<Question> getQuestionsById() {
        return questionsById;
    }

    public void setQuestionsById(Collection<Question> questionsById) {
        this.questionsById = questionsById;
    }
}
