package com.example.zadanie1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zadanie1.model.SecondUser;
import com.example.zadanie1.model.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE id = :id ")
    User getUser(String id);

    @Query("SELECT * FROM secondUsers WHERE login = :login ")
    SecondUser getSecondUser(String login);

    @Insert
    void addAll(List<User> users);

    @Insert
    void addSecondUsers(List<SecondUser> secondUsers);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM secondUsers")
    List<SecondUser> getAllSecondUsers();

    @Query("DELETE FROM users")
    void deleteAll();


}