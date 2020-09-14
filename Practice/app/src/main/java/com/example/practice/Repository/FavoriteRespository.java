package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.practice.Bean.Favorite;
import com.example.practice.Dao.FavoriteDao;
import com.example.practice.PracticeDatabase;

public class FavoriteRespository {
    private FavoriteDao favoriteDao;
    FavoriteRespository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        favoriteDao=practiceDatabase.getFavoriteDao();
    }
    void insertFavorite(Favorite... favorites){ new InsertAsyncTask(favoriteDao).execute(favorites);
    }
    Integer findFavorite(int uid){
        return favoriteDao.FindFavorite(uid);
    }
    static class InsertAsyncTask extends AsyncTask<Favorite,Void,Void>{
        private FavoriteDao favoriteDao;

        InsertAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.InsertFavorite(favorites);
            return null;
        }
    }
}
