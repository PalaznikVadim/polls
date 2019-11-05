package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.entity.enums.Required;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {
    private int id;
    private String textTitle;
    private int idType;
    private Required required;
    private int idPoll;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text_title")
    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    @Basic
    @Column(name = "id_type")
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "required")
    public Required getRequired() {
        return required;
    }

    public void setRequired(Required required) {
        this.required = required;
    }

    @Basic
    @Column(name = "id_poll")
    public int getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                idType == question.idType &&
                idPoll == question.idPoll &&
                Objects.equals(textTitle, question.textTitle) &&
                Objects.equals(required, question.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textTitle, idType, required, idPoll);
    }
}
