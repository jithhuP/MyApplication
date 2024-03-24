package com.example.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class postdata {
    @Expose
    @SerializedName("user") private String user;
    @SerializedName("data") private String data;

    public void set_data(String user, String data){
        this.user = user;
        this.data = data;
    }

    public void data_show(){
        Log.e("test", user+data);
    }
    public String get_data() {return data;}



    Gson gson = new GsonBuilder().setLenient().create();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://203.255.81.72:10021/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public void dataSend(){
        comm_data service = retrofit.create(comm_data.class);

        Call<String> call = null;
        call = service.post("six", data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("test", response.body().toString());
                //응답을 처리하는 코드
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("WARING", "");
                //실패 시 처리하는 코드
            }
        });
    }



}