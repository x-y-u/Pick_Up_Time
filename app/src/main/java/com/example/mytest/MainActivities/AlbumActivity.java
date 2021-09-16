package com.example.mytest.MainActivities;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mytest.AlbumChooseActivity;
import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.MainActivity;
import com.example.mytest.R;
import com.example.mytest.WelcomeActivity;

public class AlbumActivity extends BaseStatusBarActivity {
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_album);
        back = findViewById(R.id.album_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //两秒钟进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动MainActivity主页面，这段代码是在主线程执行
                startActivity(new Intent(AlbumActivity.this, AlbumChooseActivity.class));
                //关闭当前页面（结束WelcomeActivity）
                finish();
            }
        },1200);
    }
}
