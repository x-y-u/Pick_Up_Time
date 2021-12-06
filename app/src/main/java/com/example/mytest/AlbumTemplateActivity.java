package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytest.ItemFragments.ImageFragment;
import com.example.mytest.MyAdapter.FragmentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlbumTemplateActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView confirm;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private List<Integer> bg_images;
    private FragmentsAdapter adapter;
    private ImageView bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_template);
        back = findViewById(R.id.album_template_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm = findViewById(R.id.album_template_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(3,intent);
                finish();
            }
        });
        viewPager = findViewById(R.id.template_vp);
        fragments = new ArrayList<>();
        bg_images = new ArrayList<>();
        Intent intent = getIntent();
        int num = intent.getIntExtra("num",1);
        if (num == 1){
            fragments.add(new ImageFragment(R.drawable.template_bg1_1));
            fragments.add(new ImageFragment(R.drawable.template_bg2_1));
            bg_images.add(R.drawable.template_bg1);
            bg_images.add(R.drawable.template_bg2);
        }else {
            fragments.add(new ImageFragment(R.drawable.template_bg3_1));
            fragments.add(new ImageFragment(R.drawable.template_bg4_1));
            bg_images.add(R.drawable.template_bg3);
            bg_images.add(R.drawable.template_bg4);
        }
        bg = findViewById(R.id.template_bg);
        adapter = new FragmentsAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bg.setImageResource(bg_images.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}