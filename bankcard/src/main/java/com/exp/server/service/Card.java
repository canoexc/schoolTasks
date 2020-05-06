package com.exp.server.service;

public interface Card {
    int login(String id,String pwd);
    int query(String id);
    int withdraw(String id,int m);
    int topUp(String id,int m);
    int state(String id);
}
