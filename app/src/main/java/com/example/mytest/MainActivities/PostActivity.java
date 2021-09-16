package com.example.mytest.MainActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.Dialogs.PostCommentBottomDialog;
import com.example.mytest.ExhibitionActivity;
import com.example.mytest.MyAdapter.PostRecyclerViewAdapter;
import com.example.mytest.R;
import com.example.mytest.db.PostBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostActivity extends BaseStatusBarActivity {
    private TextView tv_title;
    private CardView card_to_pictures_collection;
    private RecyclerView recyclerView;
    private PostRecyclerViewAdapter adapter;
    private String s;
    private List<PostBean> postBeans;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_post);
        InitViews();
        InitDatas();
        InitEvents();
        getPost();
    }

    private void InitEvents() {
        card_to_pictures_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostActivity.this, ExhibitionActivity.class));
            }
        });
        adapter.setOnCommentListener(new PostRecyclerViewAdapter.OnCommentListener() {
            @Override
            public void OnComment(int pos) {
                PostCommentBottomDialog dialog = PostCommentBottomDialog.newInstance(postBeans.get(pos).getComments());
                dialog.setListener(new PostCommentBottomDialog.OnCommentConfirmListener() {
                    @Override
                    public void OnConfirm() {
                        adapter.add_comment(pos);
                    }
                });
                dialog.show(getSupportFragmentManager(),"comment");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitViews() {
        tv_title = findViewById(R.id.post_tv_title);
        card_to_pictures_collection = findViewById(R.id.post_to_picture_web);
        recyclerView = findViewById(R.id.post_rv);
        back = findViewById(R.id.post_back);
    }

    private void InitDatas() {
        postBeans = new ArrayList<>();
        adapter = new PostRecyclerViewAdapter(this,postBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getPost(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                String url = "http://120.27.195.193:8080/queryPostsList";
                try {
                    s = HttpUtils.post(url,json.toString());
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0;i<jsonArray.length();i++){
                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        postBeans.add(new PostBean(jsonObject.getInt("postsid"),jsonObject.getString("username"),
                                jsonObject.getString("head_image"),jsonObject.getString("date"),jsonObject.getString("content"),
                                jsonObject.getString("first_image"),jsonObject.getInt("agree_num"),jsonObject.getInt("comment_num")));
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
            //adapter.setImages(images);
            adapter.setPost(postBeans);
        }
    };
}
