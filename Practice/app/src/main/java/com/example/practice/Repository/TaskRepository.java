package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.Task;
import com.example.practice.Dao.TaskDao;
import com.example.practice.PracticeDatabase;

import java.util.List;

class TaskRepository {
    private TaskDao taskDao;
    TaskRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        taskDao=practiceDatabase.getTaskDao();
    }
    void insertTasks(Task... tasks){ new InsertAsyncTask(taskDao).execute(tasks);}
    List<Task> findAllTasks(List<Integer> tkids){
        return taskDao.FindAllTasks(tkids);
    }
    List<Task> findTasksWithPattern(String pattern){
        return taskDao.FindTasksWithPattern("%"+pattern+"%");
    }
    static class InsertAsyncTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;
        InsertAsyncTask(TaskDao taskDao){this.taskDao=taskDao;}
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.InsertTask(tasks);
            return null;
        }
    }
}
