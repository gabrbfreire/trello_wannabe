package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Service
@SessionAttributes("userId")
public class UserService {

    @Autowired
    private UserRepository repo;
    //private Integer userId;

//    public int getUserId() {
//        return userId;
//    }

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
        n.setUser_email(email);
        n.setUser_password(password);
        repo.save(n);
        return "Saved";

    }

    //Finds user by email and checks password
    public User findUserByEmail(String email, String password) {

        //Gets data from repository
        List<User> userList = repo.findUserByEmail(email);
        //userId = userList.get(0).getUser_id();

        if(userList.isEmpty()){
            return null;
        }else{
            if(passwordCorrect(password, userList)){
                return userList.get(0);
            }else{
                return null;
            }
        }
    }

    public Boolean passwordCorrect(String password, List<User> userList){
        if(password.equals(userList.get(0).getUser_password())){
            return true;
        }else{
            return false;
        }
    }

}
