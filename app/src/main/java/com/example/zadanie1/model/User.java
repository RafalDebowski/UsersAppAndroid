package com.example.zadanie1.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long userID;
    private String id;
    private String screenname;
    private String api;

    public User(String id, String screenname, String api) {
        this.id = id;
        this.screenname = screenname;
        this.api = api;
    }

    public String getId() {
        return id;
    }

    public String getScreenname() {
        return screenname;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
