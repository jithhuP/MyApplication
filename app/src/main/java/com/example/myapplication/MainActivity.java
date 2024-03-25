package com.example.myapplication;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View.OnClickListener;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.bluetooth.le.BluetoothLeAdvertiser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class MainActivity extends AppCompatActivity {

    // BLE 클래스 내의 스캔 콜백
    private BluetoothAdapter.LeScanCallback scanCallbackLe;

    // BLE 클래스의 인스턴스 생성
    private BLE ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_add = findViewById(R.id.bt_add);
        Button bt_delete = findViewById(R.id.bt_delete);
        EditText ed_text = findViewById(R.id.ed_text);
        TextView tv = findViewById(R.id.tv);

        // BLE 클래스의 인스턴스 생성
        ble = new BLE();

        bt_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // "추가" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
                // Bluetooth 장치 스캔 시작
                ble.startBLEScan(MainActivity.this);
            }
        });

        bt_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                // "삭제" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
            }
        });

        // BLE 클래스 내의 스캔 콜백 설정
        scanCallbackLe = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                // BLE 장치에서 데이터를 수집하고 서버에 전송하는 코드
                String MacAdd = device.getAddress();
                String data = ble.byteArrayToHex(scanRecord);
                Log.d("BLE", "Scanned Device: " + MacAdd + ", Data: " + data);

                // UI 업데이트를 위해 메인 스레드에서 TextView에 데이터 출력
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TextView에 스캔된 데이터 출력
                        tv.setText("Scanned Device: " + MacAdd + "\nData: " + data);
                    }
                });

                // 서버에 데이터 전송
                ble.postBLEDataToServer(data);
            }
        };
    }
}

