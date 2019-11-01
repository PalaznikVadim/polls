package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answer {
    private int id;
    private String text;
    private Question questionByIdQuestion;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @ManyToOne
    @JoinColumn(name = "id_question", referencedColumnName = "id", nullable = false)
    public Question getQuestionByIdQuestion() {
        return questionByIdQuestion;
    }

    public void setQuestionByIdQuestion(Question questionByIdQuestion) {
        this.questionByIdQuestion = questionByIdQuestion;
    }
}
