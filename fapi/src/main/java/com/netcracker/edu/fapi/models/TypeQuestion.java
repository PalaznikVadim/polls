package com.netcracker.edu.fapi.models;

public class TypeQuestion {
    private int id;
    private String type;

    public TypeQuestion() {
    }

    public TypeQuestion(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeQuestion{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
