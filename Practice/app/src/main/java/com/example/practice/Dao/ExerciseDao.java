package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void InsertExercise(Exercise... exercises);
    @Query("SELECT * FROM Exercise")
    List<Exercise> getAllExercise();
    @Query("SELECT * FROM Exercise WHERE NAME LIKE:pattern")
    List<Exercise> getExerciseWithPattern(String pattern);
}
