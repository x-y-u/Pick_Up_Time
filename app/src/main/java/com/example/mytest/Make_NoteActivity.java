package com.example.mytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Make_NoteActivity extends AppCompatActivity {
    private ImageView back;
    private TextView tv_confirm;
    private EditText et_title,et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__note);
        InitViews();
        InitEvents();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tv_confirm.setTextSize(14);
                        break;
                    case MotionEvent.ACTION_UP:
                        tv_confirm.setTextSize(16);
                        //finish();
                        break;
                }
                return false;
            }
        });
    }

    private void InitViews() {
        back = findViewById(R.id.make_note_back);
        tv_confirm = findViewById(R.id.make_note_confirm);
        et_title = findViewById(R.id.make_note_title);
        et_content = findViewById(R.id.make_note_content);
    }
}