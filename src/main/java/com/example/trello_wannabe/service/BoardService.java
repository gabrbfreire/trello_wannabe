package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repo;
    private UserService userService;

    public List<Board> getBoards(User user){
        List<Board> boardList = repo.selectBoardsByUserId(user.getUser_id());
        return boardList;
    }

    public void createBoard(String boardName, User user){
        Board newBoard = new Board();
        newBoard.setBoard_name(boardName);
        newBoard.setUser_id_user(user.getUser_id());
        repo.save(newBoard);
    }

    public void updateBoard(Integer boardId, String boardName){
        Optional<Board> boardData = repo.findById(boardId);
        Board board = boardData.get();
        board.setBoard_name(boardName);
        repo.save(board);
    }

    public void deleteBoard(Integer boardId){
        repo.deleteById(boardId);
    }
}
