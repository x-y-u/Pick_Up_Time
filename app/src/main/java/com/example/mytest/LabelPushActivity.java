package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mytest.MyAdapter.LabelsRecyclerViewAdapter;
import com.example.mytest.MyAdapter.PushRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class LabelPushActivity extends AppCompatActivity {
    private ImageButton back;
    private RecyclerView labels_rv,images_rv;
    private String url = "http://120.27.195.193/files/QuanJiaFu/1/image/1-0.jpeg";
    private String url1 = "http://www.51pptmoban.com/d/file/2014/09/18/b5b0bfe1ce6ad1d1f073a17845ea7dc0.jpg";
    private PushRecyclerViewAdapter image_adapter;
    private LabelsRecyclerViewAdapter labels_adapter;
    private List<String> images,labels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_push);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitDatas() {
        images = new ArrayList<>();
        labels = new ArrayList<>();
        for(int i= 1971;i<2022;i++){
            labels.add(i+"");
        }
        labels_adapter = new LabelsRecyclerViewAdapter(this,labels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        labels_rv.setLayoutManager(linearLayoutManager);
        labels_rv.setAdapter(labels_adapter);

        for (int i = 0; i < 10 ; i++){
            images.add(url);
            images.add(url1);
        }
        images_rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        image_adapter = new PushRecyclerViewAdapter(this,images);
        images_rv.setAdapter(image_adapter);
    }

    private void InitViews() {
        back = findViewById(R.id.label_back);
        labels_rv = findViewById(R.id.labels_rv);
        images_rv = findViewById(R.id.label_images_rv);
    }
}