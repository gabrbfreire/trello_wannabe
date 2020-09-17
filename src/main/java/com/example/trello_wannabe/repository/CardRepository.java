package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "CALL SelectCardsByBoardId(:boardId);", nativeQuery = true)
    List<Card> selectCardsByListId(@Param("boardId") Integer boardId);

    @Procedure(name = "CreateCard")
    void createCard(@Param("cardTitle") String cardTitle, @Param("listId") Integer listId, @Param("userId") Integer userId, @Param("boardId") Integer boardId);

    @Procedure(name = "UpdateCardTitle")
    void updateCardTitle(@Param("cardId") Integer cardId, @Param("cardTitle") String cardTitle, @Param("userId") Integer userId);

    @Procedure(name = "UpdateCardList")
    void updateCardList(@Param("cardId") Integer cardId, @Param("listId") Integer listId, @Param("userId") Integer userId);
}
