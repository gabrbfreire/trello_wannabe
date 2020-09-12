package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Board;
import com.example.trello_wannabe.entity.Card;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.service.BoardService;
import com.example.trello_wannabe.service.CardService;
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
    @Autowired
    private CardService cardService;


    // -----USER-----
    // Login
    // Get user
    @ModelAttribute("user") //Session model
    @PostMapping(path="/login")
    public User getAllUsers(@RequestParam String email, @RequestParam String password) {
        try{
            return userService.findUserByEmail(email, password);
        }catch (Exception e){
            return null;
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



    // -----CARDS-----
    // Get Cards
    @GetMapping(path = "/cards")
    public ResponseEntity<List<Card>> getCardsByBoardId(@RequestParam Integer boardId, @SessionAttribute("user") User user){
        try {
            return new ResponseEntity<>(cardService.getCards(boardId, user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Card
    @PostMapping(path = "/cards")
    public ResponseEntity<HttpStatus> createCard(@RequestParam String title, @RequestParam Integer boardId, @SessionAttribute("user") User user){
        try{
            cardService.createCard(title, boardId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Card Title
    @PutMapping(path = "/cards/updateTitle")
    public ResponseEntity<HttpStatus> updateCardTitle(@RequestParam Integer cardId, @RequestParam String newTitle, @SessionAttribute("user") User user){
        try {
            cardService.updateCardTitle(cardId, newTitle, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Card Board
    @PutMapping(path = "/cards/updateBoard")
    public ResponseEntity<HttpStatus> updateCardBoard(@RequestParam Integer cardId, @RequestParam Integer newBoard, @SessionAttribute("user") User user){
        try {
            cardService.updateCardBoard(cardId, newBoard, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Card
    @DeleteMapping(path = "/cards")
    public ResponseEntity<HttpStatus> deleteCard(@RequestParam Integer cardId, @SessionAttribute("user") User user){
        try {
            cardService.deleteCard(cardId, user);
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