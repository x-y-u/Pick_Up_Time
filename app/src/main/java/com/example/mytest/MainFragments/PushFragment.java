package com.example.mytest.MainFragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mytest.ImageActivity;
import com.example.mytest.LabelPushActivity;
import com.example.mytest.MyAdapter.PushRecyclerViewAdapter;
import com.example.mytest.NoteActivity;
import com.example.mytest.PersonalActivity;
import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class PushFragment extends Fragment implements View.OnClickListener{
    private View view;
    private ImageButton to_personal;
    private RecyclerView recyclerView;
    private List<String> images;
    private String url = "http://120.27.195.193/files/QuanJiaFu/1/image/1-0.jpeg";
    private String url1 = "http://www.51pptmoban.com/d/file/2014/09/18/b5b0bfe1ce6ad1d1f073a17845ea7dc0.jpg";
    private PushRecyclerViewAdapter adapter;
    //private TextView tv_simple,tv_newest,tv_hottest;
    private TextView[] titles = new TextView[3];
    private RelativeLayout[] img_to = new RelativeLayout[4];
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_push,container,false);
        InitViews();
        InitDatas();
        InitEvents();
        select(0);
        return view;
    }

    private void InitDatas() {
        images = new ArrayList<>();
        for (int i = 0; i < 10 ; i++){
            images.add(url);
            images.add(url1);
        }
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new PushRecyclerViewAdapter(getActivity(),images);
        recyclerView.setAdapter(adapter);
    }

    private void InitEvents() {
        to_personal.setOnClickListener(this);
//        tv_hottest.setOnClickListener(this::onClick);
//        tv_newest.setOnClickListener(this::onClick);
//        tv_simple.setOnClickListener(this::onClick);
        for(int i = 0;i<titles.length;i++){
            titles[i].setOnClickListener(this);
        }
        for (int i = 0;i<img_to.length;i++){
            img_to[i].setOnClickListener(this);
        }
    }

    private void InitViews() {
        to_personal = view.findViewById(R.id.to_personal);
        recyclerView = view.findViewById(R.id.push_rv);
        titles[2] = view.findViewById(R.id.tv_hottest);
        titles[0] = view.findViewById(R.id.tv_simple);
        titles[1] = view.findViewById(R.id.tv_newest);
        img_to[0] = view.findViewById(R.id.img_to_time_label);
        img_to[1] = view.findViewById(R.id.img_to_address_label);
        img_to[2] = view.findViewById(R.id.img_to_note);
        img_to[3] = view.findViewById(R.id.img_to_sign_in);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.to_personal:
                startActivity(new Intent(getActivity(), PersonalActivity.class));
                break;
            case R.id.tv_simple:
                select(0);
                break;
            case R.id.tv_newest:
                select(1);
                break;
            case R.id.tv_hottest:
                select(2);
                break;
            case R.id.img_to_time_label:
                intent = new Intent(getActivity(), LabelPushActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.img_to_address_label:
                intent = new Intent(getActivity(), LabelPushActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.img_to_note:
                intent = new Intent(getActivity(), NoteActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.img_to_sign_in:
                break;
        }
    }

    private void reset(){
        for (int i = 0;i < titles.length;i++){
            titles[i].setTextSize(17);
            titles[i].setTextColor(getResources().getColor(R.color.text_unselected));
        }
    }

    private void select(int i) {
        reset();
        titles[i].setTextColor(getResources().getColor(R.color.text_selected));
        titles[i].setTextSize(22);
    }
}