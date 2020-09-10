package com.example.trello_wannabe.entity;

import javax.persistence.*;

@Entity(name = "cards")
@NamedStoredProcedureQuery(
        name = "CreateCard",
        procedureName = "CreateCard",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardTitle", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "boardId", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class)
        }
        )
@NamedStoredProcedureQuery(
        name = "UpdateCardTitle",
        procedureName = "UpdateCardTitle",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardTitle", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class)
        }
)
@NamedStoredProcedureQuery(
        name = "UpdateCardBoard",
        procedureName = "UpdateCardBoard",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "boardId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class)
        }
)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;
    private String card_title;
    private Integer boards_board_id;
    private Integer user_id;

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
