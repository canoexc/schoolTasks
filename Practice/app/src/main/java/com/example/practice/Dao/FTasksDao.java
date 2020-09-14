package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.FTasks;

import java.util.List;

@Dao
public interface FTasksDao {
    @Insert
    void InsertFTasks(FTasks... fTasks);
    @Query("SELECT TKID FROM FTasks WHERE FID IN(:fids)")
    List<Integer> FindAllTasks(List<Integer> fids);
    @Delete
    void deleteFtask(FTasks...fTasks);
}
