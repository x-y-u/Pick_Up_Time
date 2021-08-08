package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mytest.Common.MyViewPager;
import com.example.mytest.ItemFragments.EventFragment;
import com.example.mytest.ItemFragments.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private MyViewPager viewPager;
    private EventsAdapter eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        InitViews();
        InitDatas();
    }

    private void InitDatas() {
        fragments = new ArrayList<>();
        for (int i=0;i<6;i++){
            fragments.add(new EventFragment());
        }
        eventsAdapter = new EventsAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(eventsAdapter);
    }

    private void InitViews() {
        viewPager = findViewById(R.id.events_vp);
        viewPager.setIsCanScroll(false);
    }
}