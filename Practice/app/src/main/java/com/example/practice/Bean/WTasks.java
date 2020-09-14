package com.example.practice.Bean;

import androidx.room.Entity;

@Entity(primaryKeys={"wid","tkid"})
public class WTasks {
    private int wid;
    private int tkid;
    public WTasks(int wid, int tkid) {
        this.wid = wid;
        this.tkid = tkid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
    }
}
