package com.netcracker.edu.fapi.models.viewModels;

import org.springframework.validation.ObjectError;

import java.sql.Date;
import java.util.List;

public class ViewUser {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String role;
    private String email;
    private String password;

    private List<ObjectError> errors;

    public ViewUser() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ViewUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", errors=" + errors +
                '}';
    }
}
