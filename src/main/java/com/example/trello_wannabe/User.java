package com.example.trello_wannabe;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    private String email_user;

    private String password_user;


    public Integer getId() {
        return id_user;
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