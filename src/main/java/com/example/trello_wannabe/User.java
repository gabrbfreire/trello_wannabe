package com.example.trello_wannabe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;

    private String email_user;

    private String password_user;

    public Integer getId() {
        return id_user;
    }

    public void setId(Integer id) {
        this.id_user = id;
    }

    public String getEmail() {
        return email_user;
    }

    public void setEmail(String email) {
        this.email_user = email;
    }

    public void setPassword(String password) {
        this.password_user = password;
    }

    public String getPassword() {
        return password_user;
    }
}