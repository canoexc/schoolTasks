package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practice.Bean.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void UserInsert(User... users);
    @Update
    void UserUpdate(User... users);
    @Query("SELECT * FROM USER WHERE USERNAME in (:username) AND PASSWORD in (:password)")
    List<User> LoginCheck(String username, String password);
    @Query("SELECT * FROM USER WHERE USERNAME IN (:username)")
    List<User> UniqueCheck(String username);
    @Query("DELETE FROM USER")
    void DeleteUser();
}
