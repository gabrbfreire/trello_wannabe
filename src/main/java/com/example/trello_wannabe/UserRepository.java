package com.example.trello_wannabe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {

    // Stored Procedures
    @Query(value = "CALL SelectUserByEmail(?1)", nativeQuery = true)
    List<User> findUserByEmail(String email);

}