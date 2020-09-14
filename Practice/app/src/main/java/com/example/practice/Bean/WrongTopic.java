package com.example.practice.Bean;

import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity
public class WrongTopic {
    @PrimaryKey(autoGenerate = true)
    private int wid;
    private int uid;

    public WrongTopic(int uid) {
        this.uid = uid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
