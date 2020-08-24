package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repo;
    private String email;

    @Autowired
    public UserService(UserRepository repo){
        this.repo = repo;
    }


    public List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user){
        repo.save(user);
    }

    public User get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    //Inserts new user into the database
    public String addNewUser(String email, String password){

        User n = new User();
        n.setEmail(email);
        n.setPassword(password);
        this.save(n);
        return "Saved";

    }

    //Finds user by email and checks password
    public String findUserByEmail(String email, String password) {
        this.email = email;

        //Gets data from repository
        List<User> userList = repo.findUserByEmail(email);

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

    public String findUserBoards(){
        List<User> userList = repo.findUserByEmail(email);

        System.out.println(userList);
        return "userList.get(0).toString()";
    }
}
