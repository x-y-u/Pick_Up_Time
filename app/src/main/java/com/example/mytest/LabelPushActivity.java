package com.example.mytest;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.Dialogs.LabelBottomDialog;
import com.example.mytest.MyAdapter.LabelsRecyclerViewAdapter;
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter1;
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabelPushActivity extends BaseStatusBarActivity {
    private ImageButton back;
    private RecyclerView labels_rv,images_rv;
    private PushImageRecyclerViewAdapter3 image_adapter;
    private LabelsRecyclerViewAdapter labels_adapter;
    private List<String> images,labels,image_ids;
    private TextView tv_checked,tv_labels_dialog;
    private String s;
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
        tv_labels_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabelBottomDialog dialog = LabelBottomDialog.newInstance(labels);
                dialog.setListener(new LabelBottomDialog.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        tv_checked.setText(labels.get(position));
                        getImages(labels.get(position));
                    }
                });
                dialog.show(getSupportFragmentManager(),"label_bottom_dialog");
            }
        });
    }

    private void InitDatas() {
        images = new ArrayList<>();
        labels = new ArrayList<>();
        image_ids = new ArrayList<>();
        Intent intent = getIntent();
        labels = intent.getStringArrayListExtra("labels");
        labels_adapter = new LabelsRecyclerViewAdapter(this,labels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        labels_rv.setLayoutManager(linearLayoutManager);
        labels_adapter.setOnItemClickListener(new LabelsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnClick(String label_text) {
                tv_checked.setText(label_text);
                getImages(label_text);
            }
        });
        labels_rv.setAdapter(labels_adapter);
        images_rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        image_adapter = new PushImageRecyclerViewAdapter3(this,images);
        images_rv.setAdapter(image_adapter);
        tv_checked.setText(labels.get(0));
        getImages(labels.get(0));
    }

    private void InitViews() {
        back = findViewById(R.id.label_back);
        labels_rv = findViewById(R.id.labels_rv);
        images_rv = findViewById(R.id.label_images_rv);
        tv_checked = findViewById(R.id.label_checked_tv);
        tv_labels_dialog = findViewById(R.id.labels_list_tv);
    }

    private void getImages(String label_text){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                String url = "http://120.27.195.193:8080/queryESByKeyWord";
                try {
                    json.put("keyword",label_text);
                    s = HttpUtils.post(url,json.toString());
                    JSONArray jsonArray = new JSONArray(s);
                    images.clear();
                    image_ids.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        images.add(jsonObject.getString("url"));
                        image_ids.add(jsonObject.getString("resourceid"));
                    }
                    Message msg = new Message();
                    handler.sendMessage(msg);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            image_adapter.setImages(images);
            image_adapter.setImage_ids(image_ids);
        }
    };
}