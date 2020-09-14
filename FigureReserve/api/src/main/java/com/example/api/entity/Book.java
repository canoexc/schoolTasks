package com.example.api.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private int bid;
    private int uid;
    private int pid;
    private int state;
    private String address;
    private String phone;
    private String time;

    public Book(int uid, int pid, int state, String address, String phone, String time) {
        this.uid = uid;
        this.pid = pid;
        this.state = state;
        this.address = address;
        this.phone = phone;
        this.time = time;
    }
    public Book(){};
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
