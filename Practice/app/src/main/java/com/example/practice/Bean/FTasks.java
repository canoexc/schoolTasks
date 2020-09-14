package com.example.practice.Bean;

import androidx.room.Entity;

@Entity(primaryKeys={"fid","tkid"})
public class FTasks {
    private int fid;
    private int tkid;

    public FTasks(int fid, int tkid) {
        this.fid = fid;
        this.tkid = tkid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
    }
}
