package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.service.BoardService;
import com.example.trello_wannabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController // This means that this class is a Controller
@SessionAttributes("user")  //Session attributes
public class MainController{
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;


    // Sign up
    @PostMapping(path="/signup") // Map ONLY POST Requests
    String addUser (@RequestParam String email, @RequestParam String password){

        //Inserts new user into the database
        return userService.addNewUser(email, password);
    }

    // Get all users for login
    @ModelAttribute("user") //Session model
    @PostMapping(path="/login")
    User getAllUsers(@RequestParam String email, @RequestParam String password) {

        //Finds user by email and checks password
        return userService.findUserByEmail(email, password);
    }



    // Get Boards
    @GetMapping(path="/boards/get")
    List<Board> getAllUserBoards(@SessionAttribute("user") User user) {

        return boardService.findBoards(user);
    }

    // Add Board
    @PostMapping(path="/boards")
    String addBoard(@RequestParam String boardName, @SessionAttribute("user") User user) {

        return boardService.addBoard(boardName, user);
    }

    // +++++++++++++++++++++ PRINTS SESSION ++++++++++++++++++++++++++
    @GetMapping(path="/boards/showSession")
    User showSession(@SessionAttribute("user") User user) {

        return user;
    }

    @PutMapping(path="/boards/updateBoard")
    String updateBoard(Integer boardId, String boardNewName){

        return boardService.updateBoard(boardId, boardNewName);
    }




    // Logout
    @GetMapping(path="/logout")
    String logout(HttpSession session){
        session.invalidate();
        return "Logged out";
    }
}