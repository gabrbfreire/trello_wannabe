package com.example.trello_wannabe.entity;

import javax.persistence.*;

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer board_id;
    private String board_name;
    private Integer user_id_user;

    public Integer getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Integer board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public Integer getUser_id_user() {
        return user_id_user;
    }

    public void setUser_id_user(Integer user_id_user) {
        this.user_id_user = user_id_user;
    }
}
