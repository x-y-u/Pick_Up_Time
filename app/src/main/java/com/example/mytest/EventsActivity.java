package com.example.mytest;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.MyViewPager;
import com.example.mytest.ItemFragments.EventFragment;
import com.example.mytest.MyAdapter.FragmentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends BaseStatusBarActivity {
    private List<Fragment> fragments;
    private MyViewPager viewPager;
    private FragmentsAdapter fragmentsAdapter;
    private List<Boolean> is_selected;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        InitViews();
        InitDatas();
    }

    private void InitDatas() {
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);
        fragments = new ArrayList<>();
        is_selected = new ArrayList<>();
        for (int i=0;i<20;i++){
            fragments.add(new EventFragment(pos*20+i));
            is_selected.add(false);
        }
        fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragmentsAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        viewPager.setIsCanScroll(true);
        is_selected.set(0,true);
        ((EventFragment)fragments.get(0)).LoadDatas();
    }

    private void InitViews() {
        viewPager = findViewById(R.id.events_vp);
        viewPager.setIsCanScroll(false);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            ((EventFragment)fragments.get(position)).LoadDatas();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}