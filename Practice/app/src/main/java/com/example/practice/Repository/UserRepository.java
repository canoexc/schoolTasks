package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.practice.Bean.User;
import com.example.practice.Dao.UserDao;
import com.example.practice.PracticeDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private LiveData<List<User>> allUsersLive;
    private UserDao userDao;

    public UserRepository(Context context){
        PracticeDatabase practiceDatabase = PracticeDatabase.getDatabase(context.getApplicationContext());
        userDao = practiceDatabase.getUserDao();
    }
    //增
    public void insertUsers(User... users){new InsertAsyncTask(userDao).execute(users);}
    //改
    public void updateUsers(User... users){new UpdateAsyncTask(userDao).execute(users);}
    //删
    public void deleteUsers(){new DeleteAsyncTask(userDao).execute();}
    //登录校验
    public List<User>loginCheck(String username,String password){
        try {
            return new LoginCheckAsyncTask(userDao).execute(username,password).get();
        }catch (ExecutionException e){
            Log.d("mylog","执行异常了");
            return null;
        }catch (InterruptedException e){
            Log.d("mylog","中断异常了");
            return null;
        }
    }
    //重复校验
    public List<User>uniqueCheck(String username){
        try {
            return new UniqueCheckAsyncTask(userDao).execute(username).get();
        }catch (ExecutionException e){
            Log.d("mylog","执行异常了");
            return null;
        }catch (InterruptedException e){
            Log.d("mylog","中断异常了");
            return null;
        }
    }
    static class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        InsertAsyncTask(UserDao userDao){this.userDao=userDao;}
        @Override
        protected Void doInBackground(User... users) {
            userDao.UserInsert(users);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        UpdateAsyncTask(UserDao userDao){this.userDao=userDao;}
        @Override
        protected Void doInBackground(User... users) {
            userDao.UserUpdate(users);
            return null;
        }
    }
    static class LoginCheckAsyncTask extends AsyncTask<String,Void,List<User>>{
        private UserDao userDao;
        LoginCheckAsyncTask(UserDao userDao){this.userDao=userDao;}
        @Override
        protected List<User> doInBackground(String... strings) {
            return userDao.LoginCheck(strings[0],strings[1]);
        }
    }
    static class UniqueCheckAsyncTask extends AsyncTask<String,Void,List<User>>{
        private UserDao userDao;
        UniqueCheckAsyncTask(UserDao userDao){this.userDao=userDao;}
        @Override
        protected List<User> doInBackground(String... strings) {
            return userDao.UniqueCheck(strings[0]);
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;
        DeleteAsyncTask(UserDao userDao){this.userDao=userDao;}
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.DeleteUser();
            return null;
        }
    }
}
