package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mytest.Common.ImgUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class ImageActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final int REQUEST_CODE_SAVE_IMG = 10;
    private ImageButton imageButton;
    private ImageView imageView;
    private int imageurl;
    private Bitmap bitmap;
    private Button image_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        image_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });
    }

    private void InitDatas() {
        Intent intent = getIntent();
        //imageurl = intent.getStringExtra("imageurl");
        imageurl = intent.getIntExtra("imageurl",R.drawable.on_loading);
        Glide.with(this).load(imageurl).into(new ImageViewTarget<Drawable>(imageView){
            //??????????????????
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                super.onResourceReady(resource, transition);
                // ??????????????????
                imageView.setImageDrawable(resource);
                bitmap = drawableToBitmap(resource);
            }
            @Override
            protected void setResource(@Nullable Drawable resource) {

            }
        });
        imageView.setAdjustViewBounds(true);
    }

    private void InitViews() {
        imageView = findViewById(R.id.dialog_image);
        imageButton = findViewById(R.id.image_back);
        image_save = findViewById(R.id.image_save);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
                .getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        saveImage();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //?????????????????????????????????
            new AppSettingsDialog.Builder(this).build().show();

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            //??????sd????????????
            String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //String[] mPermissionList = new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                Log.i("11","1");
                //???????????????
                saveImage();
            } else {
                //????????????,??????????????????????????????????????????
                Log.i("11","0");
                EasyPermissions.requestPermissions(
                        this,  //?????????
                        "????????????????????????sd????????????", //????????????
                        REQUEST_CODE_SAVE_IMG, //?????????
                        mPermissionList //????????????
                );
                //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},1);
            }
        } else {
            saveImage();
        }
    }

    private void saveImage() {
        boolean isSaveSuccess = ImgUtils.saveImageToGallery(this, bitmap);
        if (isSaveSuccess) {
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "????????????????????????????????????", Toast.LENGTH_SHORT).show();
        }
    }
}