package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.service.BoardService;
import com.example.trello_wannabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    // -----USER-----
    // Login
    // Get user
    @ModelAttribute("user") //Session model
    @PostMapping(path="/login")
    public ResponseEntity<User> getAllUsers(@RequestParam String email, @RequestParam String password) {
        try{
            return new ResponseEntity<>(userService.findUserByEmail(email, password), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Sign up
    // Create user
    @PostMapping(path="/signup") // Map ONLY POST Requests
    public ResponseEntity<HttpStatus> addUser (@RequestParam String email, @RequestParam String password){
        try{
            userService.addNewUser(email, password);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO UpdateUserEmail
    //todo UpdateUserPassword

    // Logout
    @GetMapping(path="/logout")
    public ResponseEntity<HttpStatus> logout(HttpSession session){
        try {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // -----BOARDS-----
    // Get Boards
    @GetMapping(path="/boards/get")
    public ResponseEntity<List<Board>> getAllUserBoards(@SessionAttribute("user") User user) {
        try {
            return new ResponseEntity<>(boardService.getBoards(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Board
    @PostMapping(path="/boards")
    public ResponseEntity<HttpStatus> createBoard(@RequestParam String boardName, @SessionAttribute("user") User user) {
        try {
            boardService.createBoard(boardName, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Board
    @PutMapping(path="/boards")
    public ResponseEntity<HttpStatus> updateBoard(@RequestParam Integer boardId, @RequestParam String boardNewName){
        try {
            boardService.updateBoard(boardId, boardNewName);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Board
    @DeleteMapping(path="/boards")
    public ResponseEntity<HttpStatus> deleteBoard(@RequestParam Integer boardId){
        try {
            boardService.deleteBoard(boardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // +++++++++++++++++++++ PRINTS SESSION ++++++++++++++++++++++++++
    @GetMapping(path="/boards/showSession")
    public User showSession(@SessionAttribute("user") User user) {
        return user;
    }
}