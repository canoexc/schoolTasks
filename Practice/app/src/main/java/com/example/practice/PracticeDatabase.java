package com.example.practice;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practice.Bean.ETasks;
import com.example.practice.Bean.Exercise;
import com.example.practice.Bean.FTasks;
import com.example.practice.Bean.Favorite;
import com.example.practice.Bean.Task;
import com.example.practice.Bean.User;
import com.example.practice.Bean.WTasks;
import com.example.practice.Bean.WrongTopic;
import com.example.practice.Dao.ETasksDao;
import com.example.practice.Dao.ExerciseDao;
import com.example.practice.Dao.FTasksDao;
import com.example.practice.Dao.FavoriteDao;
import com.example.practice.Dao.TaskDao;
import com.example.practice.Dao.UserDao;
import com.example.practice.Dao.WTasksDao;
import com.example.practice.Dao.WrongTopicDao;

@Database(entities = {ETasks.class, Exercise.class, Favorite.class, FTasks.class,
        Task.class, User.class, WrongTopic.class, WTasks.class},version = 1,exportSchema = false)
public abstract class PracticeDatabase extends RoomDatabase {
    private static PracticeDatabase INSTANCE;
    public static synchronized PracticeDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PracticeDatabase.class,"practice_databse")
                    .build();
        }
        return INSTANCE;
    }
    public abstract ETasksDao getETaskDao();
    public abstract ExerciseDao getExerciseDao();
    public abstract FavoriteDao getFavoriteDao();
    public abstract FTasksDao getFTasksDao();
    public abstract TaskDao getTaskDao();
    public abstract UserDao getUserDao();
    public abstract WrongTopicDao getWrongTopicDao();
    public abstract WTasksDao getWTasksDao();

}
