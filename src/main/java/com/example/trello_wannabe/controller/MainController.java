package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.service.BoardService;
import com.example.trello_wannabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path="/login")
    public ResponseEntity<HttpStatus> getAllUsers(@RequestParam String email, @RequestParam String password) {
        try{
            if(createSession(email, password)!=null){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ModelAttribute("user") //Session model
    private User createSession(String email, String password){

        return userService.findUserByEmail(email, password);
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
    // Get Email for header
    @GetMapping(path="/boards/getEmail")
    public ResponseEntity<String> getUserEmail(@SessionAttribute("user") User user) {
        try {
            return new ResponseEntity<>(user.getUser_email(), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> updateBoard(@RequestParam Integer boardId, @RequestParam String boardNewName, @SessionAttribute("user") User user){
        try {
            boardService.updateBoard(boardId, boardNewName, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Board
    @DeleteMapping(path="/boards")
    public ResponseEntity<HttpStatus> deleteBoard(@RequestParam Integer boardId, @SessionAttribute("user") User user){
        try {
            boardService.deleteBoard(boardId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // +++++++++++++++++++++ PRINTS SESSION ++++++++++++++++++++++++++
    @GetMapping(path="/boards/showSession")
    public User showSession(@SessionAttribute("user") User user) {
        try {
            return user;
        }catch (Exception e){
            return null;
        }
    }
}