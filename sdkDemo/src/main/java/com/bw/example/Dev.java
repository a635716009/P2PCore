package com.bw.example;

/**
 * Created by Administrator on 2017/4/19.
 */

class Dev {
    private String DID;
    private String userName;
    private String password;

    boolean logined;
    boolean opened;
    boolean isPlaying;

    public Dev(String DID, String userName, String password) {
        this.DID = DID;
        this.userName = userName;
        this.password = password;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("DID:%s\nusername:%s  password:%s",DID,userName,password);
    }
}
