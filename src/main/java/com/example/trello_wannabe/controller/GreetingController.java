package com.example.trello_wannabe.controller;

import com.example.trello_wannabe.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    public String boards(@SessionAttribute("user") User user){
        if(user.getUser_id()!=0){
            return "boards";
        }else {
            return "login";
        }
    }
}