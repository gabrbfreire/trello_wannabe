package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Card;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@SessionAttributes("currentBoardId")  //Session attributes
public class CardController {

    @Autowired
    private CardService cardService;

    // Store current board
    @PostMapping(path = "/boards/storeBoardId")
    public ResponseEntity<HttpStatus> storeBoardId(@RequestParam Integer boardId){

        try {
            sessionBoardId(boardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Store current board
    @ModelAttribute("currentBoardId") //Session model
    public Integer sessionBoardId(@RequestParam Integer boardId){

        return boardId;
    }


    // -----CARDS-----
    // Get Cards
    @GetMapping(path = "/cards/get")
    public ResponseEntity<List<Card>> getCardsByBoardId(@SessionAttribute("currentBoardId") Integer boardId, @SessionAttribute("user") User user){
        try {
            return new ResponseEntity<>(cardService.getCards(boardId, user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Create Card
    @PostMapping(path = "/cards")
    public ResponseEntity<HttpStatus> createCard(@RequestParam String title, @SessionAttribute("currentBoardId") Integer boardId, @SessionAttribute("user") User user){
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
    @GetMapping(path="/cards/showSession")
    public Integer showSession(@SessionAttribute("currentBoardId") Integer currentBoardId) {
        try {
            return currentBoardId;
        }catch (Exception e){
            return null;
        }
    }
}
