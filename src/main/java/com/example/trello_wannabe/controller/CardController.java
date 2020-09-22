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
    public ResponseEntity<List<Card>> getCardsByListId(@SessionAttribute("currentBoardId") Integer boardId, @SessionAttribute("user") User user){
        try {
            return new ResponseEntity<>(cardService.getCards(boardId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Create Card
    @PostMapping(path = "/cards")
    public ResponseEntity<HttpStatus> createCard(@RequestParam String title, @RequestParam Integer listId, @RequestParam Integer cardIndex, @SessionAttribute("user") User user, @SessionAttribute("currentBoardId") Integer boardId){
        try{
            cardService.createCard(title, listId, user, boardId, cardIndex);
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

    // Update Card Index
    @PutMapping(path = "/cards/updateIndex")
    public ResponseEntity<HttpStatus> updateCardIndex(@RequestParam Integer cardId, @RequestParam Integer newIndex, @SessionAttribute("user") User user){
        try {
            cardService.updateCardIndex(cardId, newIndex, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Card List
    @PutMapping(path = "/cards/updateList")
    public ResponseEntity<HttpStatus> updateCardList(@RequestParam Integer cardId, @RequestParam Integer newList, @SessionAttribute("user") User user){
        try {
            cardService.updateCardList(cardId, newList, user);
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
