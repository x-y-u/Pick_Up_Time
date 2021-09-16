package com.example.mytest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.Common.MyViewPager;
import com.example.mytest.ItemFragments.PushAfterSearchFragment;
import com.example.mytest.ItemFragments.PushBeforeSearchFragment;
import com.example.mytest.MyAdapter.FragmentsAdapter;
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PushSearchActivity extends BaseStatusBarActivity {
    private ImageButton back;
    private EditText et_search;
    private TextView tv_search;
    private MyViewPager viewPager;
    private FragmentsAdapter adapter;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_search);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitDatas() {
        fragments = new ArrayList<>();
        fragments.add(new PushBeforeSearchFragment());
        fragments.add(new PushAfterSearchFragment());
        adapter = new FragmentsAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setIsCanScroll(false);
    }

    private void InitEvents() {
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                et_search.clearFocus();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    viewPager.setCurrentItem(0);
                }
            }
        });
    }

    private void InitViews() {
        back = findViewById(R.id.push_search_back);
        et_search = findViewById(R.id.push_search_et);
        tv_search = findViewById(R.id.push_search_tv);
        viewPager = findViewById(R.id.push_search_vp);
    }
}