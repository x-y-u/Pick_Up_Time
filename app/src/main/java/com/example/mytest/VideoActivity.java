package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private String[] urls = new String[4];
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        urls[0] = "http://120.27.195.193/files/3dimages/time1/video1.mp4";
        urls[1] = "http://120.27.195.193/files/3dimages/time2/video2.mp4";
        urls[2] = "http://120.27.195.193/files/3dimages/time3/video3.mp4";
        urls[3] = "http://120.27.195.193/files/3dimages/time4/video4.mp4";
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        back = findViewById(R.id.video_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //网络数据
        Uri uri = Uri.parse(urls[index]);
        videoView = findViewById(R.id.video);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}