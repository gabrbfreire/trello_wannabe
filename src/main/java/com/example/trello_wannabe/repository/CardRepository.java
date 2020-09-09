package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "CALL SelectCardsByBoardId(:boardId);", nativeQuery = true)
    List<Card> selectCardsByBoardId(@Param("boardId") Integer id);
}
