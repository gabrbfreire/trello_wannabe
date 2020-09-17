package com.example.trello_wannabe.repository;
import com.example.trello_wannabe.entity.Listt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListRepository extends JpaRepository<Listt, Integer> {

    @Query(value = "CALL SelectListsByBoardId(:boardId);", nativeQuery = true)
    List<Listt> SelectListsByBoardId(@Param("boardId") Integer boardId);

    @Procedure(name = "UpdateList")
    void UpdateList(@Param("listId") Integer listId, @Param("listNewTitle") String listNewTitle);
}
