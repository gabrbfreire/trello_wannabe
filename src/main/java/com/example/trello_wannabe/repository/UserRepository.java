package com.example.trello_wannabe.repository;

import com.example.trello_wannabe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {

    // Stored Procedures
    @Query(value = "CALL SelectUserByEmail(:email)", nativeQuery = true)
    List<User> findUserByEmail(@Param("email") String email);
}