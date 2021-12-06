package com.example.mytest.MainActivities;


import android.content.Intent;
import android.os.Bundle;

import android.provider.MediaStore;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.ItemFragments.PushFragment1;
import com.example.mytest.ItemFragments.PushFragment2;
import com.example.mytest.ItemFragments.PushFragment3;
import com.example.mytest.ItemFragments.PushFragment4;
import com.example.mytest.LabelPushActivity;
import com.example.mytest.PushSearchActivity;
import com.example.mytest.R;
import com.example.mytest.xytest.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class PushActivity extends BaseStatusBarActivity implements View.OnClickListener{
    private ImageButton push_scanning,back;
    private List<String> images,image_ids;
    private List<String> time_list,address_list;
    private TextView[] textViews = new TextView[4];
    private ImageView[] imageViews = new ImageView[2];
    private PushFragment1 fragment1;
    private PushFragment2 fragment2;
    private PushFragment3 fragment3;
    private PushFragment4 fragment4;
    private FragmentManager fragmentManager;
    private EditText et_to_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_push);
        InitViews();
        InitDatas();
        InitEvents();
        select(0);
    }

    private void InitDatas() {
        fragmentManager = getSupportFragmentManager();
        images = new ArrayList<>();
        image_ids = new ArrayList<>();
        time_list = new ArrayList<>();
        time_list.add("1920年");
        time_list.add("1921年");
        time_list.add("1922年");
        time_list.add("1924年");
        time_list.add("1928年");
        time_list.add("1929年");
        time_list.add("1935年");
        time_list.add("1939年");
        time_list.add("1949年");
        time_list.add("1953年");
        time_list.add("1955年");
        time_list.add("1956年");
        time_list.add("1957年");
        time_list.add("1959年");
        time_list.add("1960年");
        time_list.add("1964年");
        time_list.add("1972年");
        time_list.add("1977年");
        time_list.add("1978年");
        time_list.add("1982年");
        time_list.add("1983年");
        time_list.add("1984年");
        time_list.add("1991年");
        time_list.add("2000年");
        time_list.add("2003年");
        time_list.add("2005年");
        time_list.add("2008年");
        time_list.add("2012年");
        time_list.add("2014年");
        time_list.add("2016年");
        time_list.add("2019年");
        time_list.add("2020年");
        time_list.add("2021年");
        time_list.add("2022年");
        address_list = new ArrayList<>();
        address_list.add("杭州");
        address_list.add("宁波");
        address_list.add("温州");
        address_list.add("嘉兴");
        address_list.add("湖州");
        address_list.add("绍兴");
        address_list.add("金华");
        address_list.add("衢州");
        address_list.add("舟山");
        address_list.add("台州");
        address_list.add("丽水");
    }

    private void InitEvents() {
        push_scanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        for (int i=0;i<4;i++){
            textViews[i].setOnClickListener(this);
        }
        for (int i=0;i<2;i++){
            imageViews[i].setOnClickListener(this);
        }
        et_to_search.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void InitViews() {
        push_scanning = findViewById(R.id.push_scanning);
        textViews[0] = findViewById(R.id.push_tab1);
        textViews[1] = findViewById(R.id.push_tab2);
        textViews[2] = findViewById(R.id.push_tab3);
        textViews[3] = findViewById(R.id.push_tab4);
        imageViews[0] = findViewById(R.id.push_to_time_iv);
        imageViews[1] = findViewById(R.id.push_to_address_iv);
        et_to_search = findViewById(R.id.push_to_search_et);
        back = findViewById(R.id.push_back);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.push_tab1:
                select(0);
                break;
            case R.id.push_tab2:
                select(1);
                break;
            case R.id.push_tab3:
                select(2);
                break;
            case R.id.push_tab4:
                select(3);
                break;
            case R.id.push_to_time_iv:
                intent = new Intent(this,LabelPushActivity.class);
                intent.putStringArrayListExtra("labels", (ArrayList<String>) time_list);
                startActivity(intent);
                break;
            case R.id.push_to_address_iv:
                intent = new Intent(this,LabelPushActivity.class);
                intent.putStringArrayListExtra("labels", (ArrayList<String>) address_list);
                startActivity(intent);
                break;
            case R.id.push_to_search_et:
                startActivity(new Intent(this, PushSearchActivity.class));
                break;
            case R.id.push_back:
                finish();
                break;
        }
    }

    private void reset(){
        for (int i=0;i<4;i++){
            textViews[i].setTextColor(getResources().getColor(R.color.text_unselected));
            textViews[i].setTextSize(17);
        }
    }

    private void select(int i) {
        reset();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        textViews[i].setTextSize(19);
        textViews[i].setTextColor(getResources().getColor(R.color.text_selected_red));
        switch (i){
            case 0:
                if (fragment1 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment1 = new PushFragment1();
                    transaction.add(R.id.push_content, fragment1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment1);
                }
                break;
            case 1:
                if (fragment2 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment2 = new PushFragment2();
                    transaction.add(R.id.push_content, fragment2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment2);
                }
                break;
            case 2:
                if (fragment3 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment3 = new PushFragment3();
                    transaction.add(R.id.push_content, fragment3);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment3);
                }
                break;
            case 3:
                if (fragment4 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment4 = new PushFragment4();
                    transaction.add(R.id.push_content, fragment4);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment4);
                }
                break;
        }
        transaction.commit();
    }

        private void hideFragments(FragmentTransaction transaction){
            if (fragment1 != null) {
                transaction.hide(fragment1);
            }
            if (fragment2 != null) {
                transaction.hide(fragment2);
            }
            if (fragment3 != null) {
                transaction.hide(fragment3);
            }
            if (fragment4 != null) {
                transaction.hide(fragment4);
            }
    }
}