package com.example.mytest;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mytest.Common.BaseActivity;
import com.example.mytest.MainFragments.OldPushFragment;
import com.example.mytest.MainFragments.MemoryFragment;
import com.example.mytest.MainFragments.AlbumFragment;
import com.example.mytest.MainFragments.PostFragment;
import com.example.mytest.MainFragments.PushFragment;
import com.example.mytest.MainFragments.RelaxFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mtab1,mtab2,mtab4,mtab5,mtab3;
    private ImageView img1,img2,img3,img4,img5;
    private PushFragment pushFragment;
    private MemoryFragment memoryFragment;
    private AlbumFragment albumFragment;
    private PostFragment postFragment;
    private RelaxFragment relaxFragment;
    private FragmentManager fragmentManager;
    private Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        fragmentManager = getSupportFragmentManager();
        InitEvents();
        //InitDatas();
        setTabSelection(0);
    }

    private void InitEvents() {
        mtab1.setOnClickListener(MainActivity.this);
        mtab2.setOnClickListener(MainActivity.this);
        mtab3.setOnClickListener(MainActivity.this);
        mtab4.setOnClickListener(MainActivity.this);
        mtab5.setOnClickListener(MainActivity.this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_push:
                setTabSelection(0);
                break;
            case R.id.tab_memory:
                setTabSelection(1);
                break;
            case R.id.tab_album:
                setTabSelection(2);
                break;
            case R.id.tab_post:
                setTabSelection(3);
                break;
            case R.id.tab_relax:
                setTabSelection(4);
                break;
        }
    }

    private void InitViews(){
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.album);

        mtab1 = findViewById(R.id.tab_push);
        mtab2 = findViewById(R.id.tab_memory);
        mtab3 = findViewById(R.id.tab_album);
        mtab4 = findViewById(R.id.tab_post);
        mtab5 = findViewById(R.id.tab_relax);

        img1 = findViewById(R.id.img_push);
        img2 = findViewById(R.id.img_memory);
        img3 = findViewById(R.id.img_album);
        img4 = findViewById(R.id.img_post);
        img5 = findViewById(R.id.img_relax);
    }


    private void resetImgs(int pos) {
        img1.setImageResource(R.mipmap.push_unselected);
        img2.setImageResource(R.mipmap.memory_unselected);
        img4.setImageResource(R.mipmap.post_unselected);
        img5.setImageResource(R.mipmap.relax_unselected);
        if (pos!=2){
            img3.setImageBitmap(bmp);
        }
    }

    private void setTabSelection(int pos){
        // 每次选中之前先清楚掉上次的选中状态
        resetImgs(pos);
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (pos){
            case 0:
                img1.setImageResource(R.mipmap.push_selected);
                if (pushFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    pushFragment = new PushFragment();
                    transaction.add(R.id.content, pushFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(pushFragment);
                }
                break;
            case 1:
                img2.setImageResource(R.mipmap.memory_selected);
                if (memoryFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    memoryFragment = new MemoryFragment();
                    transaction.add(R.id.content, memoryFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(memoryFragment);
                }
                break;
            case 2:
                img3.setImageBitmap(ScaleToFit(bmp,0.65f));
                if (albumFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    albumFragment = new AlbumFragment();
                    transaction.add(R.id.content, albumFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(albumFragment);
                }
                break;
            case 3:
                img4.setImageResource(R.mipmap.post_selected);
                if (postFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    postFragment = new PostFragment();
                    transaction.add(R.id.content, postFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(postFragment);
                }
                break;
            case 4:
                img5.setImageResource(R.mipmap.relax_selected);
                if (relaxFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    relaxFragment = new RelaxFragment();
                    transaction.add(R.id.content, relaxFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(relaxFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction){
        if (pushFragment != null) {
            transaction.hide(pushFragment);
        }
        if (memoryFragment != null) {
            transaction.hide(memoryFragment);
        }
        if (albumFragment != null) {
            transaction.hide(albumFragment);
        }
        if (postFragment != null) {
            transaction.hide(postFragment);
        }
        if (relaxFragment != null) {
            transaction.hide(relaxFragment);
        }
    }
    //图片放大缩小
    public Bitmap ScaleToFit(Bitmap bmp, float scale) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap bmResult = Bitmap.createBitmap(bmp,0,0,width,height,matrix,true);
        return bmResult;
    }

}