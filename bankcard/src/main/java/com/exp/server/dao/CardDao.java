package com.exp.server.dao;

public interface CardDao {
    int loginDao(String id,String pwd);
    int queryDao(String id);
    int withdrawDao(String id,int m);
    int topUpDao(String id,int m);
    int queryLockDao(String id);
    int lockDao(String id);
    int deadLock(String id);
    void loggerDao(String id,int m);
}
