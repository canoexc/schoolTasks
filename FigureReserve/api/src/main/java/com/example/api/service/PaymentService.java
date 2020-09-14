package com.example.api.service;

public interface PaymentService {
    public int getBalance(int uid);
    public int updateBalance(int uid,int balance);
    public int insertAccount(int uid);
    public String getPwd(int uid);
}
