package edu.ntut.finalproject.models;

public class User {

    private String uid;
    private String name;

    public User() {
        uid  = "";
        name = "";
    }

    public User(String uid, String name) {
        this.uid  = uid;
        this.name = name;
    }

    public String getUid() { return uid; }
    public String getName() { return name; }

    public void setUid(String uid) { this.uid = uid; }
    public void setName(String name) { this.name = name; }

}
