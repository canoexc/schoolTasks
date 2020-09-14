package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void InsertTask(Task... tasks);
    @Query("SELECT * FROM TASK WHERE TKID IN (:tkids)")
    List<Task> FindAllTasks(List<Integer> tkids);
    @Query("SELECT * FROM TASK WHERE TITLE LIKE:pattern")
    List<Task> FindTasksWithPattern(String pattern);
}
