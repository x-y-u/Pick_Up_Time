package com.example.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Dialogs.LoadingDialog;

public class MakeMusicAlbumActivity extends BaseStatusBarActivity {
    private Button bt_choose_img,bt_choose_template,bt_loading;
    private ImageButton back;
    private int request_code = 1;
    private int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_music_album);
        back = findViewById(R.id.make_music_album_back);
        bt_choose_img = findViewById(R.id.make_music_album_choose_image);
        bt_choose_template = findViewById(R.id.make_music_album_choose_template);
        bt_loading = findViewById(R.id.make_music_album_loading);
        Intent intent = getIntent();
        state = intent.getIntExtra("state",0);
        if (state != 0){
            bt_choose_img.setText("选择图片(已完成)");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MakeMusicAlbumActivity.this,ChooseImage2Activity.class),request_code);
            }
        });
        bt_choose_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MakeMusicAlbumActivity.this,AlbumTemplateActivity.class);
                intent1.putExtra("num",2);
                startActivityForResult(intent1,request_code);
            }
        });
        bt_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog dialog = LoadingDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"loading");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Intent intent = new Intent(MakeMusicAlbumActivity.this,HtmlActivity.class);
                        intent.putExtra("url","file:///android_asset/hot_ship/index.html");
                        startActivity(intent);
                    }
                },3000);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("1111",resultCode+"");
        if (resultCode == 2){
            bt_choose_img.setText("选择图片(已完成)");
        }else if (resultCode == 3){
            bt_choose_template.setText("选择模板(已完成)");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}