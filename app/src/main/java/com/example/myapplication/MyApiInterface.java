package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyApiInterface {
    @POST("api/path")
    Call<MyResponse> postData(@Body MyData data);
}
