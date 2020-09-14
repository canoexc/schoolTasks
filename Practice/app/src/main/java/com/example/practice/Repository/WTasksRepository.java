package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.WTasks;
import com.example.practice.Dao.WTasksDao;
import com.example.practice.PracticeDatabase;

class WTasksRepository {
    private WTasksDao wTasksDao;
    WTasksRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        wTasksDao = practiceDatabase.getWTasksDao();
    }
    void insertWTasks(WTasks... wTasks){new InsertAsyncTask(wTasksDao).execute(wTasks);
    }
    static class InsertAsyncTask extends AsyncTask<WTasks,Void,Void>{
        private WTasksDao wTasksDao;
        InsertAsyncTask(WTasksDao wTasksDao){
            this.wTasksDao=wTasksDao;
        }
        @Override
        protected Void doInBackground(WTasks... wTasks) {
            wTasksDao.InsertWTasks(wTasks);
            return null;
        }
    }
}
