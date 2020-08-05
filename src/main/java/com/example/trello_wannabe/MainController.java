package com.example.trello_wannabe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller // This means that this class is a Controller
public class MainController{
    @Autowired
    private UserService userService;

    //Inserts new user into the database
    @PostMapping(path="/signup") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String email, @RequestParam String password){

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setEmail(email);
        n.setPassword(password);
        userService.save(n);
        return "Saved";

    }

    //Finds user by email and checks password
    @PostMapping(path="/login")
    public @ResponseBody
    String getAllUsers(@RequestParam String email, @RequestParam String password) {
        List<User> userList = userService.findUserByEmail(email);
        if(userList.isEmpty()){
            return "User not found";
        }else{
            if(password.equals(userList.get(0).getPassword())){
                return "Valid";
            }else{
                return "Password incorrect";
            }
        }
    }
}