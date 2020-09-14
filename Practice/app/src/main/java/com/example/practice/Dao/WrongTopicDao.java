package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.WrongTopic;

@Dao
public interface WrongTopicDao {
    @Insert
    void InsertWrongTopic(WrongTopic... wrongTopics);
    @Query("SELECT WID FROM WrongTopic WHERE UID IN(:uid)")
    Integer FindWrongTopic(int uid);
}
