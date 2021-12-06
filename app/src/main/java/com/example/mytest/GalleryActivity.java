package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class GalleryActivity extends AppCompatActivity {
    private WebView webView;
    private String[] urls = new String[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gallery);
        webView = findViewById(R.id.gallery_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        urls[0] = "http://120.27.195.193/files/3dimages/time1/index.html";
        urls[1] = "http://120.27.195.193/files/3dimages/time2/index.html";
        urls[2] = "http://120.27.195.193/files/3dimages/time3/index.html";
        urls[3] = "http://120.27.195.193/files/3dimages/time4/index.html";
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        webView.loadUrl(urls[index]);
    }
}