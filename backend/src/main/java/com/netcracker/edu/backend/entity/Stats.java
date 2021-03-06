package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Stats {
    private int id;
    private int idAnswer;
    private int count;
    private double percent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_answer", unique = true)
    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "percent")
    public Double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return id == stats.id &&
                idAnswer == stats.idAnswer &&
                Objects.equals(count, stats.count) &&
                Objects.equals(percent, stats.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idAnswer, count, percent);
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
