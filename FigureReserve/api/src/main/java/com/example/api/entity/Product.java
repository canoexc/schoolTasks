package com.example.api.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private int pid;
    private String pname;
    private String detail;
    private int pre;
    private int after;
    private int state;
    private Object img;
    public Product(){};
    public Product(String pname, String detail, int pre, int after, int state, Object img) {
        this.pname = pname;
        this.detail = detail;
        this.pre = pre;
        this.after = after;
        this.state = state;
        this.img = img;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getAfter() {
        return after;
    }

    public void setAfter(int after) {
        this.after = after;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }
}
