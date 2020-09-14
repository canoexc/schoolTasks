package com.example.reserve.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.entity.User;
import com.example.api.service.UserService;
import com.example.reserve.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service(version = "1.0.0" ,interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> checkUserLogin(String username, String pwd) {
        return userMapper.checkUserLogin(username,pwd);
    }

    @Override
    public List<User> checkUsernameUni(String username) {
        return userMapper.checkUsernameUni(username);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
