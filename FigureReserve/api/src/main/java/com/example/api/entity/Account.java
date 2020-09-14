package com.example.api.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private int aid;
    private int uid;
    private String pwd;
    private int balance;

    public Account(int uid, String pwd, int balance) {
        this.uid = uid;
        this.pwd = pwd;
        this.balance = balance;
    }
    public Account(){};
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
