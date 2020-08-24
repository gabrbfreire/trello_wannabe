package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class MainController{

    private UserService userService;

    @Autowired
    public MainController(UserService userService){
        this.userService = userService;
    }


    @PostMapping(path="/signup") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String email, @RequestParam String password){

        //Inserts new user into the database
        return userService.addNewUser(email, password);
    }


    @PostMapping(path="/login")
    public @ResponseBody
    String getAllUsers(@RequestParam String email, @RequestParam String password) {

        //Finds user by email and checks password
        return userService.findUserByEmail(email, password);
    }


    @PostMapping(path="/boards")
    public @ResponseBody
    String getAllBoards() {


        return userService.findUserBoards();
    }
}