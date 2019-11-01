package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.entity.enums.Role;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String password;
    private Collection<Poll> pollsById;

    public User() {
    }

    public User(int id, String name, String surname, Date dateOfBirth, Role role, String email, String password, Collection<Poll> pollsById) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.email = email;
        this.password = password;
        this.pollsById = pollsById;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Date_of_Birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Basic
    @UniqueElements
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(role, user.role) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dateOfBirth, role, email, password);
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<Poll> getPollsById() {
        return pollsById;
    }

    public void setPollsById(Collection<Poll> pollsById) {
        this.pollsById = pollsById;
    }
}
