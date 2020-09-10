package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "CALL SelectCardsByBoardId(:boardId,:userId);", nativeQuery = true)
    List<Card> selectCardsByBoardId(@Param("boardId") Integer boardId, @Param("userId") Integer userId);

    @Procedure(name = "CreateCard")
    void createCard(@Param("cardTitle") String cardTitle, @Param("boardId") Integer boardId, @Param("userId") Integer userId);

    @Procedure(name = "UpdateCardTitle")
    void updateCardTitle(@Param("cardId") Integer cardId, @Param("cardTitle") String cardTitle, @Param("userId") Integer userId);

    @Procedure(name = "UpdateCardBoard")
    void updateCardBoard(@Param("cardId") Integer cardId, @Param("boardId") Integer boardId, @Param("userId") Integer userId);
}