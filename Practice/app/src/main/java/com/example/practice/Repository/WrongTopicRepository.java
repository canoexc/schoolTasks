package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.WrongTopic;
import com.example.practice.Dao.WrongTopicDao;
import com.example.practice.PracticeDatabase;

class WrongTopicRepository {
    private WrongTopicDao wrongTopicDao;
    WrongTopicRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        wrongTopicDao=practiceDatabase.getWrongTopicDao();
    }
    void insertWrongTopic(WrongTopic... wrongTopics){ new InsertAsyncTask(wrongTopicDao).execute(wrongTopics);}
    Integer findWrongTopic(int uid){
        return wrongTopicDao.FindWrongTopic(uid);
    }
    static class InsertAsyncTask extends AsyncTask<WrongTopic,Void,Void>{
        private WrongTopicDao wrongTopicDao;
        InsertAsyncTask(WrongTopicDao wrongTopicDao){
            this.wrongTopicDao=wrongTopicDao;
        }
        @Override
        protected Void doInBackground(WrongTopic... wrongTopics) {
            wrongTopicDao.InsertWrongTopic(wrongTopics);
            return null;
        }
    }
}
