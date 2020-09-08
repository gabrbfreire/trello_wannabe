package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(value = "CALL SelectBoardsByUserId(:user_id);", nativeQuery = true)
    List<Board> selectBoardsByUserId(@Param("user_id") Integer id);

}
