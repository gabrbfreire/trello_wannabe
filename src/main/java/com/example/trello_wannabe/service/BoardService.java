package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoards(User user){

        List<Board> boardList = boardRepository.selectBoardsByUserId(user.getUser_id());
        return boardList;
    }

    public void createBoard(String boardName, User user){

        Board newBoard = new Board();
        newBoard.setBoard_name(boardName);
        newBoard.setUser_id_user(user.getUser_id());
        boardRepository.save(newBoard);
    }

    public void updateBoard(Integer boardId, String boardNewName, User user){

        boardRepository.updateBoard(boardId, boardNewName, user.getUser_id());
    }

    public void deleteBoard(Integer boardId, User user){

        boardRepository.deleteBoard(boardId, user.getUser_id());
    }
}
