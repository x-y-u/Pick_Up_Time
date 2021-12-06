package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter4;

import java.util.ArrayList;
import java.util.List;

public class ChooseImage2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PushImageRecyclerViewAdapter4 adapter;
    private List<Integer> images;
    private Boolean state;
    private TextView tv_confirm;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image2);
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
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(2,intent);
                finish();
            }
        });
    }

    private void InitDatas() {
        state = false;
        images = new ArrayList<>();
        images.add(R.drawable.search_result1);
        images.add(R.drawable.search_result2);
        images.add(R.drawable.search_result3);
        images.add(R.drawable.push_5_1);
        images.add(R.drawable.search_result4);
        images.add(R.drawable.push_5_2);
        images.add(R.drawable.search_result5);
        images.add(R.drawable.search_result6);
        images.add(R.drawable.search_result7);
        images.add(R.drawable.search_result8);
        images.add(R.drawable.push_5_3);
        images.add(R.drawable.push_3_1);
        images.add(R.drawable.push_3_2);
        images.add(R.drawable.push_3_3);
        images.add(R.drawable.push_3_4);
        images.add(R.drawable.push_3_5);
        images.add(R.drawable.push_3_6);
        images.add(R.drawable.push_3_7);
        images.add(R.drawable.push_3_8);
        images.add(R.drawable.push_3_9);
        images.add(R.drawable.push_3_10);
        images.add(R.drawable.push_3_11);
        images.add(R.drawable.push_3_12);
        images.add(R.drawable.push_4_1);
        images.add(R.drawable.push_4_2);
        images.add(R.drawable.push_4_3);
        images.add(R.drawable.push_4_4);
        images.add(R.drawable.push_4_5);
        images.add(R.drawable.push_4_6);
        images.add(R.drawable.push_4_7);
        images.add(R.drawable.push_4_8);
        images.add(R.drawable.push_4_9);
        images.add(R.drawable.push_4_10);
        images.add(R.drawable.push_4_11);
        images.add(R.drawable.push_4_12);
        images.add(R.drawable.push_4_13);
        adapter = new PushImageRecyclerViewAdapter4(this,images);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new PushImageRecyclerViewAdapter4.OnItemClickListener() {
            @Override
            public void OnClick() {
            }

            @Override
            public void OnLongClick() {
                if (!state){
                    adapter.setState(true);
                    state = true;
                }
            }
        });
    }

    private void InitViews() {
        back = findViewById(R.id.choose_image2_back);
        tv_confirm = findViewById(R.id.choose_image2_confirm);
        recyclerView = findViewById(R.id.choose_image2_rv);
    }
}