package com.example.trello_wannabe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class GreetingController {

    @GetMapping("")
    public String index(){
        return "login";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signup(){
        return "signup";
    }

    //If a session exists it will user the method with the SessionAttribute
    @GetMapping("boards")
    public String boards(HttpSession session) {
        if(session.getAttribute("user")==null){
            return "login";
        }else{
            return "boards";
        }
    }

    @GetMapping("cards")
    public String cards(HttpSession session){
        if(session.getAttribute("user")==null){
            return "login";
        }else{
            return "cards";
        }
    }

}