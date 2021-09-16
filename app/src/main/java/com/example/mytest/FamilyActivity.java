package com.example.mytest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.MyAdapter.FamilyMembersAdapter;

import java.util.List;

public class FamilyActivity extends BaseStatusBarActivity {
    private FamilyMembersAdapter adapter;
    private RecyclerView members_rv;
    private ImageButton back;
    private List<String> images;
    private List<String> relationships;
    private List<String> familyids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
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
//        images = new ArrayList<>();
//        relationships = new ArrayList<>();
//        images.add(R.drawable.head_image1);
//        images.add(R.drawable.head_image2);
//        images.add(R.drawable.head_image3);
//        images.add(R.drawable.head_image4);
//        images.add(R.drawable.head_image5);
//        relationships.add("爸爸");
//        relationships.add("妈妈");
//        relationships.add("爷爷");
//        relationships.add("奶奶");
//        relationships.add("弟弟");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        images = bundle.getStringArrayList("head_images");
        relationships = bundle.getStringArrayList("relationships");
        familyids = bundle.getStringArrayList("familyids");
        adapter = new FamilyMembersAdapter(this,images,relationships,familyids);
        members_rv.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        members_rv.setAdapter(adapter);
    }

    private void InitViews() {
        back = findViewById(R.id.family_back);
        members_rv = findViewById(R.id.family_members_rv);
    }
}