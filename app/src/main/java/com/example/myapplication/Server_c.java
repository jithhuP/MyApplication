package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class Server_c {


    Gson gson = new GsonBuilder().setLenient().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://10.255.81.72:10024/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    comm_data service = retrofit.create(comm_data.class);
    Call<String> call = null;


    }


