package com.example.api.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int uid;
    private String username;
    private String pwd;
    private int authority;
    private int point;
    public User(){};
    public User(String username, String pwd, int authority,int point) {
        this.username = username;
        this.pwd = pwd;
        this.authority = authority;
        this.point = point;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
