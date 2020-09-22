package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.Listt;
import com.example.trello_wannabe.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {


    @Autowired
    private ListService listService;

    @GetMapping(path = "/lists")
    public ResponseEntity<List<Listt>> getListsByBoardId(@SessionAttribute("currentBoardId") Integer boardId){
        try {
            return new ResponseEntity<>(listService.getLists(boardId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<HttpStatus> createList(@RequestParam Integer index, @RequestParam String title, @SessionAttribute("currentBoardId") Integer boardId){
        try {
            listService.createList(title, index, boardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/lists")
    public ResponseEntity<HttpStatus> updateList(@RequestParam String newTitle, @RequestParam Integer listId){
        try {
            listService.updateListTitle(listId, newTitle);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/lists/updateIndex")
    public ResponseEntity<HttpStatus> updateListIndex(@RequestParam Integer newIndex, @RequestParam Integer listId){
        try {
            listService.updateListIndex(listId, newIndex);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/lists")
    public ResponseEntity<HttpStatus> deleteList(@RequestParam Integer listId){
        try {
            listService.deleteList(listId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
