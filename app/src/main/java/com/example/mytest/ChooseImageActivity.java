package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.MyAdapter.ChooseImagesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseImageActivity extends BaseStatusBarActivity {
    private int request_code = 1;
    private ImageButton back;
    private TextView tv_can_selected_num,tv_confirm;
    private RecyclerView images_rv;
    private List<String> images;
    private List<String> results;
    private List<Boolean> is_selected;
    private int count,can_selected_num,maxsize;
    private ChooseImagesAdapter adapter;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        Intent get_Intent = getIntent();
        maxsize = get_Intent.getIntExtra("maxsize",0);
        index = get_Intent.getIntExtra("position",0);
        InitViews();
        InitDatas();
        InitEvents();
        tv_can_selected_num.setText("还能选择"+can_selected_num+"张");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            setResult(0,getIntent());
        }
        return super.onKeyDown(keyCode,event);
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0,getIntent());
                finish();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = getIntent();
                Bundle b =new Bundle();
                for (int i = 0;i<is_selected.size();i++){
                    if (is_selected.get(i)){
                        results.add(images.get(i));
                    }
                }
                b.putStringArrayList("images", (ArrayList<String>) results);
                resultIntent.putExtras(b);
                resultIntent.putExtra("position",index);
                setResult(request_code,resultIntent);
                finish();
            }
        });
    }

    private void InitDatas() {
        count = 0;
        can_selected_num = maxsize;
        images = new ArrayList<>();
        results = new ArrayList<>();
        is_selected = new ArrayList<>();
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = this.getContentResolver();
        // 只查询jpeg和png的图片
        Cursor mCursor = mContentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);
        while (mCursor.moveToNext()) {
            count++;
            // 获取图片的路径
            String path = mCursor.getString(mCursor
                    .getColumnIndex(MediaStore.Images.Media.DATA));
            images.add(0,path);
            is_selected.add(false);
        }

        adapter = new ChooseImagesAdapter(this,images);
        adapter.setOnClickListener(new ChooseImagesAdapter.OnClickListener() {
            @Override
            public void OnClick(int pos) {
                if(!(!is_selected.get(pos)&&can_selected_num==0)){
                    ChangeState(pos);
                }
            }
        });
        images_rv.setLayoutManager(new GridLayoutManager(this,3));
        images_rv.setAdapter(adapter);
    }

    private void InitViews() {
        back = findViewById(R.id.choose_images_back);
        tv_can_selected_num = findViewById(R.id.choose_images_num);
        tv_confirm = findViewById(R.id.choose_images_confirm);
        images_rv = findViewById(R.id.choose_images_rv);
    }

    private void ChangeState(int pos){
        can_selected_num -= adapter.changeState(pos);
        if (is_selected.get(pos)){
            is_selected.set(pos,false);
        }else {
            is_selected.set(pos,true);
        }
        tv_can_selected_num.setText("还能选择"+can_selected_num+"张");
    }
}