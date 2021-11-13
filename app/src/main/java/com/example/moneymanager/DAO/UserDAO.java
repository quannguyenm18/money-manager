package com.example.moneymanager.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanager.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT*FROM user Where userName =:username")
    List<User> checkUser(String username);

    @Query("SELECT*FROM user Where passWord =:password")
    List<User> checkPass(String password);


    @Query("SELECT*FROM user Where userName LIKE '%' || :strKey || '%'")
    User searchName(String strKey);

    @Update
    void Update(User user);




}
