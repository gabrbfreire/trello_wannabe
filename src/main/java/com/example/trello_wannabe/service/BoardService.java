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
    private BoardRepository repo;
    private UserService userService;

    public List<Board> findBoards(User user){
        List<Board> boardList = repo.selectBoardsByUserId(user.getUser_id());
        return boardList;
    }

    public String addBoard(String boardName, User user){
        try{
            Board n = new Board();
            n.setBoard_name(boardName);
            n.setUser_id_user(user.getUser_id());
            repo.save(n);
            return "Saved";
        }catch (Exception e){
            return "Could not add Board";
        }
    }

    public String updateBoard(Integer BoardId, String boardName){
        try {
            repo.updateBoard(BoardId, boardName);
            return "Updated board";
        }catch (Exception e){
            return "Could not update board";
        }
    }

//    public deleteBoard(Integer BoardId){
//        repo.deleteBoard
//    }

}
