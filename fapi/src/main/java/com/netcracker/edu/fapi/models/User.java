package com.netcracker.edu.fapi.models;

import org.apache.tomcat.jni.Poll;

import java.sql.Date;
import java.util.Collection;

public class User {
    private int id;
    private String name;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String password;
    private Collection<Poll> pollsById;


    public User() {
    }

    public User(int id, String name, String role, String surname, Date dateOfBirth, String email, String password, Collection<Poll> pollsById) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.pollsById = pollsById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Poll> getPollsById() {
        return pollsById;
    }

    public void setPollsById(Collection<Poll> pollsById) {
        this.pollsById = pollsById;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", pollsById=" + pollsById +
                '}';
    }
}
