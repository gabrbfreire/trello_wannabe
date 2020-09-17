package com.example.trello_wannabe.entity;

import javax.persistence.*;

@Entity(name = "cards")
@NamedStoredProcedureQuery(
        name = "CreateCard",
        procedureName = "CreateCard",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardTitle", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "listId", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "boardId", type = Integer.class)
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
        name = "UpdateCardList",
        procedureName = "UpdateCardList",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cardId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "listId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class)
        }
)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;
    private String card_title;
    private Integer user_id;
    private Integer list_list_id;

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getList_list_id() {
        return list_list_id;
    }

    public void setList_list_id(Integer list_list_id) {
        this.list_list_id = list_list_id;
    }
}
