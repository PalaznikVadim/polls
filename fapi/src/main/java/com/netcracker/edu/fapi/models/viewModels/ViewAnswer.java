package com.netcracker.edu.fapi.models.viewModels;

import com.netcracker.edu.fapi.models.Stats;

public class ViewAnswer {
    private int id;
    private String text;
    private int idQuestion;
    private Stats stats;

    public ViewAnswer() {
    }

    public ViewAnswer(int id, String text, int idQuestion, Stats stats) {
        this.id = id;
        this.text = text;
        this.idQuestion = idQuestion;
        this.stats = stats;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
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
        return "ViewAnswer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", idQuestion=" + idQuestion +
                ", stats=" + stats +
                '}';
    }
}
