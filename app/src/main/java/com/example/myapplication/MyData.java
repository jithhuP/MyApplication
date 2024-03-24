package com.example.myapplication;

public class MyData {
    private String user;
    private String data;

    public MyData(String user, String data) {
        this.user = user;
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
