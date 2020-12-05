package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
//    public List<User> listAll(){
//        return repo.findAll();
//    }
//
//    public void save(User user){
//        repo.save(user);
//    }
//
//    public User get(Integer id){
//        return repo.findById(id).get();
//    }
//
//    public void delete(Integer id){
//        repo.deleteById(id);
//    }

    //Inserts new user into the database
    public void addNewUser(String email, String password){
        User newUser = new User();
        newUser.setUser_email(email);
        newUser.setUser_password(password);

        String passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        newUser.setUser_password(passwordHashed);
        repo.save(newUser);
    }

    //Finds user by email and checks password
    public User findUserByEmail(String email, String password) {

        //Gets data from repository
        List<User> userList = repo.findUserByEmail(email);

        if(userList.isEmpty()){
            return null;
        }else{
            if(BCrypt.checkpw(password, userList.get(0).getUser_password())){
                return userList.get(0);
            }else{
                return null;
            }
        }
    }
}
