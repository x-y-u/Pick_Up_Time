package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText username_et,password_et;
    private Button login_bt;
    private TextView tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();
        InitEvents();
    }

    private void InitEvents() {
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void InitView() {
        username_et = findViewById(R.id.acountnum);
        password_et = findViewById(R.id.password);
        login_bt = findViewById(R.id.login);
        tv_register = findViewById(R.id.register);
    }


}