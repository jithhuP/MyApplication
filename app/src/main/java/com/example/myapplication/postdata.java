package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.util.Log;


public class postdata {

    @Expose
    @SerializedName("user") private String user;
    @SerializedName("data") private String data;

    public void set_data(String user, String data){
        this.user = user;
        this.data = data;
    }
    public void data_show() {
        Log.e("test", user + data);
    }
    public  String get_data(){
        return data;
    }

}

