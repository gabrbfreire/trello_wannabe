package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(value = "CALL SelectBoardsByUserId(:user_id);", nativeQuery = true)
    List<Board> selectBoardsByUserId(@Param("user_id") Integer id);

    @Procedure(name = "UpdateBoard")
    void updateBoard(@Param("boardId") Integer boardId, @Param("boardNewName") String boardNewName, @Param("userId") Integer userId);

    @Procedure(name = "DeleteBoard")
    void deleteBoard(@Param("boardId") Integer boardId, @Param("userId") Integer userId);
}
