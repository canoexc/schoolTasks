package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.ETasks;
import com.example.practice.Dao.ETasksDao;
import com.example.practice.PracticeDatabase;

import java.util.List;

public class ETasksRepository {
    private ETasksDao eTasksDao;
    public ETasksRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        eTasksDao=practiceDatabase.getETaskDao();
    }
    public void insertETasks(ETasks... eTasks){new InsertAsyncTask(eTasksDao).execute(eTasks);}
    public List<Integer> getAllETasks(List<Integer> eids){
        return eTasksDao.FindAllTasks(eids);
    }
    static class InsertAsyncTask extends AsyncTask<ETasks,Void,Void>{
        private ETasksDao eTasksDao;
        InsertAsyncTask(ETasksDao eTasksDao){
            this.eTasksDao=eTasksDao;
        }
        @Override
        protected Void doInBackground(ETasks... eTasks) {
            eTasksDao.InsertETasks(eTasks);
            return null;
        }
    }
}
