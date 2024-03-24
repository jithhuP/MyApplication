package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import android.util.Log;

public class BluetoothC {
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://10.255.81.72:10024/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private BluetoothAdapter.LeScanCallback scanCallbackLe = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            // 여기에서 스캔된 BLE 장치를 처리합니다.
            // 예를 들어, 장치 정보를 로그에 출력할 수 있습니다:
            String MacAdd = device.getAddress();
            String data = byteArrayToHex(scanRecord);
            sendData(MacAdd, data); //데이터 이동
        }
    };

    public String byteArrayToHex(byte[] byteArray) {
        StringBuilder hex = new StringBuilder(byteArray.length * 2);
        for (byte b : byteArray) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public void sendData(String user, String data) {
        MyApiInterface apiInterface = retrofit.create(MyApiInterface.class);
        MyData myData = new MyData(user, data);
        Call<MyResponse> call = apiInterface.postData(myData);
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                Log.i("Response", "Data sent successfully");
                // 응답을 처리합니다.
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                // 실패를 처리합니다.
            }

        });
    }

}






