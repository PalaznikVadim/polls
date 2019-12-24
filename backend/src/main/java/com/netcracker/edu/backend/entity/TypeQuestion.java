package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.entity.enums.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_question", schema = "mydb", catalog = "")
public class TypeQuestion {
    private int id;
    private Type type;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeQuestion that = (TypeQuestion) o;
        return id == that.id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "TypeQuestion{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
