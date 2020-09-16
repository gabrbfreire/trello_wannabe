package com.example.trello_wannabe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("boards")
    public String boards(){
//        if(user.getUser_id()!=0){
//            return "boards";
//        }else {
//            return "login";
//        }
        return "boards";
    }

    @GetMapping("cards")
    public String cards(){
        return "cards";
    }
}