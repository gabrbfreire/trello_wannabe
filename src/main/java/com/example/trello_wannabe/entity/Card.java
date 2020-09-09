package com.example.trello_wannabe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;
    private String card_title;
    private Integer boards_board_id;
    private Integer boards_user_id_user;

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getCard_title() {
        return card_title;
    }

    public void setCard_title(String card_title) {
        this.card_title = card_title;
    }

    public Integer getBoards_board_id() {
        return boards_board_id;
    }

    public void setBoards_board_id(Integer boards_board_id) {
        this.boards_board_id = boards_board_id;
    }

    public Integer getBoards_user_id_user() {
        return boards_user_id_user;
    }

    public void setBoards_user_id_user(Integer boards_user_id_user) {
        this.boards_user_id_user = boards_user_id_user;
    }
}
