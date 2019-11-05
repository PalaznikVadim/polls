package com.netcracker.edu.fapi.models;

public class Answer {
    private int id;
    private String text;
    private int idQuestion;

    public Answer() {
    }

    public Answer(int id, String text, int idQuestion) {
        this.id = id;
        this.text = text;
        this.idQuestion = idQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", idQuestion=" + idQuestion +
                '}';
    }
}
