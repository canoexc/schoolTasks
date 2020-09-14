package com.example.api.service;
import com.example.api.entity.User;

import java.util.List;

public interface UserService {
    //登录校验
    public List<User> checkUserLogin(String username,String pwd);
    //用户名重复
    public List<User> checkUsernameUni(String username);
    //注册
    public int insertUser(User user);
    //修改
    public int updateUser(User user);
}
