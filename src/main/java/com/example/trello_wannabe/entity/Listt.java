package com.example.trello_wannabe.entity;

import javax.persistence.*;


@Entity(name = "lists")
@NamedStoredProcedureQuery(
        name = "UpdateList",
        procedureName = "UpdateList",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "listId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "listNewTitle", type = String.class),
        }
)
//UpdateListIndex
@NamedStoredProcedureQuery(
        name = "UpdateListIndex",
        procedureName = "UpdateListIndex",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "listId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "listIndex", type = Integer.class),
        }
)
public class Listt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer list_id;
    private String list_title;
    private Integer list_index;
    private Integer boards_board_id;

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public Integer getList_index() {
        return list_index;
    }

    public void setList_index(Integer list_index) {
        this.list_index = list_index;
    }

    public Integer getBoards_boards_id() {
        return boards_board_id;
    }

    public void setBoards_boards_id(Integer boards_boards_id) {
        this.boards_board_id = boards_boards_id;
    }
}
