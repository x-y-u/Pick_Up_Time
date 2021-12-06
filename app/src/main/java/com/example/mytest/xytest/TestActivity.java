package com.example.mytest.xytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mytest.AlbumChooseActivity;
import com.example.mytest.Common.CameraPreview;
import com.example.mytest.MainActivities.AlbumActivity;
import com.example.mytest.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity{
    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout preview;
    private ImageView yes,no,img;
    private Bitmap bitmap;
    private int request_code = 15;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        InitViews();
        InitEvents();
        setState(false);
        //两秒钟进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        setState(true);
                    }
                });
            }
        },2500);
    }

    private void InitEvents() {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("11",1);
                setResult(request_code,resultIntent);
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setState(false);
//                //两秒钟进入MainActivity
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCamera.takePicture(null, null, new Camera.PictureCallback() {
//                            @Override
//                            public void onPictureTaken(byte[] data, Camera camera) {
//                                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                setState(true);
//                            }
//                        });
//                    }
//                },1200);
                finish();
            }
        });
    }

    private void InitViews() {
        mCamera=Camera.open(0);;
        mPreview=new CameraPreview(this,mCamera);
        preview =(FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        yes = findViewById(R.id.take_photo_yes);
        no = findViewById(R.id.take_photo_no);
        img = findViewById(R.id.camera_img);
        //captureButton=(Button) findViewById(R.id.button_capture);


//        captureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Save save=new Save();
//                try {
//                    mCamera.takePicture(save.shutter,save.raw,save.jpeg);
//                    Toast.makeText(TestActivity.this,"拍照成功",Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(TestActivity.this,"拍照失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    private void setState(Boolean state){
        if (state){
            img.setImageBitmap(bitmap);
            img.setVisibility(View.VISIBLE);
            preview.setVisibility(View.INVISIBLE);
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
        }else {
            img.setVisibility(View.INVISIBLE);
            preview.setVisibility(View.VISIBLE);
            yes.setVisibility(View.INVISIBLE);
            no.setVisibility(View.INVISIBLE);
        }
    }
}