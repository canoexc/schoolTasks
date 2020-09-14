package com.example.payment.mapper;

import com.example.api.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PaymentMapper {
    @Select("select balance from account where uid=#{uid}")
    public int getBalance(int uid);
    @Update("update account set balance=#{balance} where uid=#{uid}")
    public int updateBalance(int uid,int balance);
    @Select("select pwd from account where uid=#{uid}")
    public String getPwd(int uid);
    @Options(useGeneratedKeys = true,keyProperty = "aid")
    @Insert("insert into account(uid,pwd,balance)")
    public int insertAccount(Account account);
}
