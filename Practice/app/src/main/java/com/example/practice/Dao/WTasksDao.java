package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.WTasks;

import java.util.List;

@Dao
public interface WTasksDao {
    @Insert
    void InsertWTasks(WTasks... wTasks);
    @Query("SELECT TKID FROM WTasks WHERE WID IN(:wids)")
    List<Integer> FindAllTasks(List<Integer> wids);
    @Delete
    void deleteWtask(WTasks...wTasks);
}
