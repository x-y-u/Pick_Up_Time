package com.example.mytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CircleImageView;
import com.example.mytest.MyAdapter.MemoryRecyclerViewAdapter;
import com.example.mytest.db.MemoryBean;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class PersonalSpaceActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView tv_remark;
    private CircleImageView head_image;
    private RecyclerView memory_rv;
    private MemoryRecyclerViewAdapter adapter;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private List<MemoryBean> memoryBeans;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_space);
        InitViews();
        InitDatas();
        InitEvents();
        setStatusBarFullTransparent();
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.indianred),
                        Math.abs(verticalOffset*1.0f)/(appBarLayout.getTotalScrollRange())));
            }
        });
    }

    private void InitDatas() {
        //memoryBeans = new ArrayList<>();
        Intent intent = getIntent();
        String imgurl = intent.getStringExtra("imageurl");
        String remark = intent.getStringExtra("relationship");
        Glide.with(this).load(imgurl).into(head_image);
        memoryBeans = (List<MemoryBean>) intent.getSerializableExtra("memoryBeans");
        adapter = new MemoryRecyclerViewAdapter(PersonalSpaceActivity.this,memoryBeans);
        memory_rv.setLayoutManager(new LinearLayoutManager(PersonalSpaceActivity.this));
        memory_rv.setAdapter(adapter);
        tv_remark.setText(remark);
    }

    private void InitViews() {
        back = findViewById(R.id.personal_space_back);
        tv_remark = findViewById(R.id.personal_space_remark);
        head_image = findViewById(R.id.personal_space_head_image);
        memory_rv = findViewById(R.id.personal_space_rv);
        appBarLayout = findViewById(R.id.personal_space_appbarlayout);
        toolbar = findViewById(R.id.personal_space_toolbar);
    }

    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}