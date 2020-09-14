package com.example.practice;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.practice.Bean.User;
import com.example.practice.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository(application);
    }
    Boolean loginCheck(String username,String password) {
        List<User> users=userRepository.loginCheck(username,password);
        if(users.size()!=0) {
            for(int i=0;i<users.size();i++)
            {
                Log.d("mylog",users.get(i).toString());
            }
            return true;
        }
        else return false;
    }
    Boolean uniqueCheck(String username){
        if(userRepository.uniqueCheck(username).size()!=0)
            return true;
        else return false;
    }
    void insertUsers(User...users){
        userRepository.insertUsers(users);
    }
    void deleteUsers(){
        userRepository.deleteUsers();
    }
}
