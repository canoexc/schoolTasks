package com.example.reserve.mapper;

import com.example.api.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username} and pwd=#{pwd}")
    public List<User> checkUserLogin(String username,String pwd);
    @Select("select * from users where username=#{username}")
    public List<User> checkUsernameUni(String username);
    @Options(useGeneratedKeys = true,keyProperty = "uid")
    @Insert("insert into users(username,pwd,authority) "+
    "values(#{username},#{pwd},#{authority})")
    public int insertUser(User user);
    @Update("update users set pwd=#{pwd} where uid=#{uid}")
    public int updateUser(User user);
}
