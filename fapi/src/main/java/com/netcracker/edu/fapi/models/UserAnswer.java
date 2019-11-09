package com.netcracker.edu.fapi.models;

import java.util.Date;

public class UserAnswer {
    private int id;
    private int idQuestion;
    private int idAnswer;
    private Date date;

    public UserAnswer() {
    }

    public UserAnswer(int id, int idQuestion, int idAnswer, Date dateTime) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.idAnswer = idAnswer;
        this.date = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Date getDateTime() {
        return date;
    }

    public void setDateTime(Date dateTime) {
        this.date = dateTime;
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", idQuestion=" + idQuestion +
                ", idAnswer=" + idAnswer +
                ", dateTime=" + date +
                '}';
    }
}
