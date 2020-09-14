package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.FTasks;
import com.example.practice.Dao.FTasksDao;
import com.example.practice.PracticeDatabase;

import java.util.List;

public class FTasksRepository {
    private FTasksDao fTasksDao;
    FTasksRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        fTasksDao=practiceDatabase.getFTasksDao();
    }
    void insertFTasks(FTasks... fTasks){ new InsertAsyncTask(fTasksDao).execute(fTasks);
    }
    List<Integer> findAllTasks(List<Integer> fids){
        return fTasksDao.FindAllTasks(fids);
    }
    static class InsertAsyncTask extends AsyncTask<FTasks,Void,Void> {
        private FTasksDao fTasksDao;
        InsertAsyncTask(FTasksDao fTasksDao){
            this.fTasksDao=fTasksDao;
        }
        @Override
        protected Void doInBackground(FTasks... fTasks) {
            fTasksDao.InsertFTasks(fTasks);
            return null;
        }
    }
}
