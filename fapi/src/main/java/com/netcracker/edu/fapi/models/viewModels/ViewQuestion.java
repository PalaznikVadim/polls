package com.netcracker.edu.fapi.models.viewModels;

import java.util.List;

public class ViewQuestion {
    private int id;
    private String textTitle;
    private String type;
    private String required;
    private int idPoll;
    private List<ViewAnswer> answers;

    public ViewQuestion() {
    }

    public ViewQuestion(int id, String textTitle, String type, String required, int idPoll, List<ViewAnswer> answers) {
        this.id = id;
        this.textTitle = textTitle;
        this.type = type;
        this.required = required;
        this.idPoll = idPoll;
        this.answers = answers;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<ViewAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ViewAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "ViewQuestion{" +
                "id=" + id +
                ", textTitle='" + textTitle + '\'' +
                ", type='" + type + '\'' +
                ", required='" + required + '\'' +
                ", idPoll=" + idPoll +
                ", answers=" + answers +
                '}';
    }
}
