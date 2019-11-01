package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Question {
    private int id;
    private String textTitle;
    private String required;
    private Collection<Answer> answersById;
    private TypeQuestion typeQuestionByIdType;
    private Poll pollByIdPoll;

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
    @Column(name = "required")
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(textTitle, question.textTitle) &&
                Objects.equals(required, question.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textTitle, required);
    }

    @OneToMany(mappedBy = "questionByIdQuestion")
    public Collection<Answer> getAnswersById() {
        return answersById;
    }

    public void setAnswersById(Collection<Answer> answersById) {
        this.answersById = answersById;
    }

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    public TypeQuestion getTypeQuestionByIdType() {
        return typeQuestionByIdType;
    }

    public void setTypeQuestionByIdType(TypeQuestion typeQuestionByIdType) {
        this.typeQuestionByIdType = typeQuestionByIdType;
    }

    @ManyToOne
    @JoinColumn(name = "id_poll", referencedColumnName = "id", nullable = false)
    public Poll getPollByIdPoll() {
        return pollByIdPoll;
    }

    public void setPollByIdPoll(Poll pollByIdPoll) {
        this.pollByIdPoll = pollByIdPoll;
    }
}
