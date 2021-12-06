package com.example.mytest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mytest.Common.BaseStatusBarActivity;

public class PersonalInfoActivity extends BaseStatusBarActivity {
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        back = findViewById(R.id.personal_info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}