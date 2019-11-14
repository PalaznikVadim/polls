package com.netcracker.edu.fapi.models;

public class Stats {
    private int id;
    private int idAnswer;
    private Integer count;
    private Double percent;

    public Stats() {
    }

    public Stats(int id, int idAnswer, Integer count, Double percent) {
        this.id = id;
        this.idAnswer = idAnswer;
        this.count = count;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", idAnswer=" + idAnswer +
                ", count=" + count +
                ", percent=" + percent +
                '}';
    }
}
