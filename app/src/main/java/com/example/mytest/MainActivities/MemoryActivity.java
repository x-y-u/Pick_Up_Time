package com.example.mytest.MainActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mytest.ChooseImageActivity;
import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.CircleImageView;
import com.example.mytest.Common.CommonUtils;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.Dialogs.LoadingDialog;
import com.example.mytest.FamilyActivity;
import com.example.mytest.MakeAlbumActivity;
import com.example.mytest.MakeMusicAlbumActivity;
import com.example.mytest.MemoryClassifyActivity;
import com.example.mytest.MemoryUploadActivity;
import com.example.mytest.MyAdapter.MemoryRecyclerViewAdapter;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemoryActivity extends BaseStatusBarActivity {
    private int request_code = 2;
    private ImageButton memory_add,memory_to_family,back;
    private ImageView[] imageViews = new ImageView[3];
    private RecyclerView memory_rv;
    private MemoryRecyclerViewAdapter adapter;
    private String userid = "1";
    private List<MemoryBean> memoryBeans;
    private View view;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_memory);
        InitViews();
        InitDatas();
        InitEvents();
        getMemory();
    }

    private void InitEvents() {
        memory_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MemoryActivity.this, MemoryUploadActivity.class),request_code);
            }
        });
        memory_to_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), FamilyActivity.class));
                get_family();
            }
        });
        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, MakeAlbumActivity.class));
            }
        });
        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, MakeMusicAlbumActivity.class));
            }
        });
        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, MemoryClassifyActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitDatas() {
        memoryBeans = new ArrayList<>();
        adapter = new MemoryRecyclerViewAdapter(this, memoryBeans);
        memory_rv.setLayoutManager(new LinearLayoutManager(this));
        memory_rv.setAdapter(adapter);
    }

    private void InitViews() {
        memory_add = findViewById(R.id.memory_add);
        memory_to_family = findViewById(R.id.memory_to_family);
        memory_rv = findViewById(R.id.memory_rv);
        imageViews[0] = findViewById(R.id.memory_circle_1);
        imageViews[1] = findViewById(R.id.memory_circle_2);
        imageViews[2] = findViewById(R.id.memory_circle_3);
        back = findViewById(R.id.memory_back);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==2){
            int year = data.getIntExtra("year",0);
            int month = data.getIntExtra("month",0);
            int day = data.getIntExtra("day",0);
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            List<String> images = data.getExtras().getStringArrayList("images");
            adapter.addMemory(new MemoryBean(userid,year,month,day,title,content,images.get(0),"周日"));
            Log.i("back",year+" "+month+" "+day+" "+title+" "+content+" "+images.size());
        }else if(resultCode == 1){
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getMemory(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                String url = "http://120.27.195.193:8080/queryMemoryInfoByID";
                try {
                    json.put("userid",userid);
                    s = HttpUtils.post(url,json.toString());
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0;i<jsonArray.length();i++){
                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String weekday = CommonUtils.num_to_weekday(jsonObject.getInt("weekday"));
                        memoryBeans.add(new MemoryBean(userid,jsonObject.getInt("year"),jsonObject.getInt("month"),
                                jsonObject.getInt("day"),jsonObject.getString("title"),jsonObject.getString("passage"),
                                jsonObject.getString("main_imageurl"),weekday));
                    }
                    Message msg = new Message();
                    handler.sendMessage(msg);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void get_family(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                String url = "http://120.27.195.193:8080/queryRelativeByID";
                try {
                    json.put("userid",userid);
                    s = HttpUtils.post(url,json.toString());
                    JSONArray jsonArray = new JSONArray(s);
                    List<String> head_images = new ArrayList<>();
                    List<String> relationships = new ArrayList<>();
                    List<String> familyids = new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        head_images.add(jsonObject.getString("head"));
                        relationships.add(jsonObject.getString("identity"));
                        familyids.add(jsonObject.getString("userid"));
                    }
                    Intent intent = new Intent(MemoryActivity.this,FamilyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("head_images", (ArrayList<String>) head_images);
                    bundle.putStringArrayList("relationships", (ArrayList<String>) relationships);
                    bundle.putStringArrayList("familyids", (ArrayList<String>) familyids);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
            //adapter.setImages(images);
            adapter.setMemory(memoryBeans);
        }
    };
}
