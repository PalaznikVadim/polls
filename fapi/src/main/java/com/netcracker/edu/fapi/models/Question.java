package com.netcracker.edu.fapi.models;

public class Question {
    private int id;
    private String textTitle;
    private int idType;
    private String required;
    private int idPoll;

    public Question() {
    }

    public Question(int id, String textTitle, int idType, String required, int idPoll) {
        this.id = id;
        this.textTitle = textTitle;
        this.idType = idType;
        this.required = required;
        this.idPoll = idPoll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public int getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", textTitle='" + textTitle + '\'' +
                ", idType=" + idType +
                ", required='" + required + '\'' +
                ", idPoll=" + idPoll +
                '}';
    }
}
