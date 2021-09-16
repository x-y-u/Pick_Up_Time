package com.example.mytest;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mytest.Common.BaseActivity;
import com.example.mytest.MainActivities.AlbumActivity;
import com.example.mytest.MainActivities.MemoryActivity;
import com.example.mytest.MainActivities.PostActivity;
import com.example.mytest.MainActivities.PushActivity;
import com.example.mytest.MainActivities.RelaxActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ImageView[] imageViews = new ImageView[5];
    private ImageView iv_personal,iv_camera;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        InitEvents();
    }

    private void InitEvents() {
        for (int i=0;i<5;i++){
            imageViews[i].setOnClickListener(this);
        }
        iv_personal.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.main_tab1:
                intent = new Intent(this,PushActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tab2:
                intent = new Intent(this,MemoryActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tab3:
                intent = new Intent(this,AlbumActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tab4:
                intent = new Intent(this,PostActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tab5:
                intent = new Intent(this,RelaxActivity.class);
                startActivity(intent);
                break;
            case R.id.main_personal:
                intent = new Intent(this,PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void InitViews(){
        imageViews[0] = findViewById(R.id.main_tab1);
        imageViews[1] = findViewById(R.id.main_tab2);
        imageViews[2] = findViewById(R.id.main_tab3);
        imageViews[3] = findViewById(R.id.main_tab4);
        imageViews[4] = findViewById(R.id.main_tab5);
        iv_personal = findViewById(R.id.main_personal);
        iv_camera = findViewById(R.id.main_camera);
    }
}