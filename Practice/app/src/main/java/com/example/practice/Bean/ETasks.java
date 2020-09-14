package com.example.practice.Bean;

import androidx.room.Entity;

@Entity(primaryKeys={"eid","tkid"})
public class ETasks {
    private int eid;
    private int tkid;

    public ETasks(int eid, int tkid) {
        this.eid = eid;
        this.tkid = tkid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
    }
}
