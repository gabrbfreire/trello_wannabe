package com.example.trello_wannabe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LogoutController {

    // Logout
    @GetMapping(path="/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }
}
