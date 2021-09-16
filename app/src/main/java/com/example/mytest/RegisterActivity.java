package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytest.Common.BaseStatusBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends BaseStatusBarActivity {
    private EditText et_username,et_password,et_phonenum;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitViews();
        InitEvents();
    }

    private void InitEvents() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String phonenum = et_phonenum.getText().toString();
                if(!username.isEmpty()&&!password.isEmpty()&&!phonenum.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"输入格式有误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InitViews() {
        et_username = findViewById(R.id.register_username);
        et_password = findViewById(R.id.register_password);
        et_phonenum = findViewById(R.id.register_phonenum);
        register = findViewById(R.id.register_confirm);
    }
}