package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
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

        bt_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = ed_text.getText().toString();
                if (rbt_hi.isChecked()) {
                    inputText = "check " + inputText;
                } else if (rbt_hello.isChecked()) {
                    inputText = "noncheck " + inputText;
                }
                tv.setText(inputText);
                // "추가" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
            }
        });

        bt_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                // "삭제" 버튼이 클릭되었을 때의 동작을 여기에 작성하세요.
            }
        });

    }


}

