package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.ETasks;

import java.util.List;

@Dao
public interface ETasksDao {
    @Insert
    void InsertETasks(ETasks... eTasks);
    @Query("SELECT TKID FROM ETasks WHERE EID IN(:eids)")
    List<Integer> FindAllTasks(List<Integer> eids);
}
