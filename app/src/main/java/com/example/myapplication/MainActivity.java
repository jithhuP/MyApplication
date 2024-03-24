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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml 파일에서 TextView의 id를 사용하여 TextView를 찾음
        //TextView tv = findViewById(R.id.tv);
        //tv.setText(tv.getText().toString() + "onCreate");

        Button bt_add = findViewById(R.id.bt_add);
        Button bt_delete = findViewById(R.id.bt_delete);
        RadioButton rbt_hi = findViewById(R.id.rbt_hi);
        RadioButton rbt_hello = findViewById(R.id.rbt_hello);
        EditText ed_text = findViewById(R.id.ed_text);
        TextView tv = findViewById(R.id.tv);

        // postdata 클래스의 인스턴스 생성
        final postdata postDataInstance = new postdata();

        bt_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // "추가" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
                // dataSend 메소드 호출
                String inputText = ed_text.getText().toString();
                //postDataInstance.set_data(inputText, "team 5");
                //postDataInstance.dataSend();
                tv.setText(inputText);
                // "추가" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
            }
        });

        bt_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String A = "A";

                tv.setText("");
                // "삭제" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
            }
        });
    }

    public class Bluetooth {
        private BluetoothAdapter.LeScanCallback scanCallbackLe = new BluetoothAdapter.LeScanCallback() {

            @Override
            public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                // 여기에서 스캔된 BLE 장치를 처리합니다.
                // 예를 들어, 장치 정보를 로그에 출력할 수 있습니다:
                String MacAdd = device.getAddress();
                String data = byteArrayToHex(scanRecord);

            }
        };

        public String byteArrayToHex(byte[] byteArray) {
            StringBuilder hex = new StringBuilder(byteArray.length * 2);
            for (byte b : byteArray) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        }

        public class ble {
            private static final long SCAN_PERIOD = 10000; // 스캔 기간을 설정합니다. 여기서는 10초로 설정했습니다.
            BluetoothAdapter blead = BluetoothAdapter.getDefaultAdapter();
            private BluetoothAdapter bluetoothAdapter;
            private Handler handler;
            private BluetoothAdapter.LeScanCallback scanCallback = new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                    String macAddress = device.getAddress();
                    String data = byteArrayToHex(scanRecord);
                    // 여기서 스캔 결과를 처리합니다.
                }
            };

            public ble() {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    // 장치가 블루투스를 지원하지 않는 경우
                    throw new RuntimeException("Device does not support Bluetooth");
                }
                handler = new Handler();
            }

            @SuppressLint("MissingPermission")
            public void startScan() {

                if (!blead.isEnabled()) {
                    blead.enable();
                }

                blead.startLeScan(scanCallback);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        blead.stopLeScan(scanCallback);
                    }
                }, SCAN_PERIOD);
            }


/*
    BluetoothAdapter blead = BluetoothAdapter.getDefaultAdapter();

    if(!blead.isEnabled())
        blead.enable();

    blead.startLeScan(scanCallback_le);
    blead.stopLeScan(scanCallback_le);



/*
private Retrofit retrofit;
Gson gson = new GsonBuilder().setLenient().create();
retrofit = new Retrofit.Builder()
            .base
*/
        }

    }
}
