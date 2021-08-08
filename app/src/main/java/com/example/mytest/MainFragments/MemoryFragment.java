package com.example.mytest.MainFragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mytest.MyAdapter.MemoryListAdapter;
import com.example.mytest.MyAdapter.MemoryYearListAdapter;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import java.util.ArrayList;
import java.util.List;

public class MemoryFragment extends Fragment {
    private ListView listView,memory_time_listview;
    private List<MemoryBean> memoryBeans;
    private List<Integer> images;
    private ImageButton time_list_show;
    private MemoryYearListAdapter memoryYearListAdapter;
    private MemoryListAdapter memoryListAdapter;
    private DrawerLayout drawerLayout;
    boolean drawer_is_open;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_memory,container,false);
        InitViews();
        InitDates();
        InitEvents();
        return view;
    }

    private void InitEvents() {
        time_list_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer_is_open){
                    drawerLayout.openDrawer(Gravity.LEFT);
                    drawer_is_open = true;
                }else {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    drawer_is_open = false;
                }
            }
        });
        memoryYearListAdapter.SetOnItemClickListener(new MemoryYearListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int year, int month) {
                memoryListAdapter.SetTime(year,month);
            }
        });
    }

    private void InitDates() {
        images = new ArrayList<>();
        memoryBeans = new ArrayList<>();
        images.add(R.drawable.head_image1);
        images.add(R.drawable.head_image2);
        images.add(R.drawable.head_image3);
        for (int i = 0;i<6;i++){
            memoryBeans.add(new MemoryBean("1",2001,7,18,8,30,
                    "我把你画成花\n未开的一朵花\n再把思念一点一滴\n画成雨落下",images,"周五"));
        }
        memoryListAdapter = new MemoryListAdapter(getActivity(),memoryBeans);
        listView.setAdapter(memoryListAdapter);
        memoryYearListAdapter = new MemoryYearListAdapter(getContext(),2018,2021);
        memory_time_listview.setAdapter(memoryYearListAdapter);
        memory_time_listview.setVerticalScrollBarEnabled(false);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawer_is_open = false;
    }

    private void InitViews() {
        listView = view.findViewById(R.id.memory_listview);
        time_list_show = view.findViewById(R.id.drawer_left_btn);
        drawerLayout = view.findViewById(R.id.memory_drawerlayout);
        memory_time_listview = view.findViewById(R.id.memory_time_list);
    }
}
