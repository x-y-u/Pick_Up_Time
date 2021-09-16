package com.example.mytest.ItemFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mytest.Common.HttpUtils;
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter1;
import com.example.mytest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PushFragment4 extends Fragment {

    private View view;
    private List<Integer> images;
    private List<String> image_ids;
    private PushImageRecyclerViewAdapter1 adapter;
    private String s;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.push_frag4,container,false);
        InitViews();
        InitDatas();
        //getImages();
        return view;
    }

    private void InitDatas() {
        image_ids = new ArrayList<>();
        images = new ArrayList<>();
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
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new PushImageRecyclerViewAdapter1(getContext(),images);
        recyclerView.setAdapter(adapter);
    }

    private void InitViews() {
        recyclerView = view.findViewById(R.id.push_frag4_rv);
    }

//    private void getImages(){
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                JSONObject json = new JSONObject();
//                String url = "http://120.27.195.193:8080/queryPhotoPartInfosList";
//                try {
//                    s = HttpUtils.post(url,json.toString());
//                    JSONArray jsonArray = new JSONArray(s);
//                    images.clear();
//                    image_ids.clear();
//                    for (int i=0;i<jsonArray.length();i++){
//                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        images.add(jsonObject.getString("url"));
//                        image_ids.add(jsonObject.getString("resourceid"));
//                    }
//                    Message msg = new Message();
//                    handler.sendMessage(msg);
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            adapter.setImages(images);
//            adapter.setImage_ids(image_ids);
//        }
//    };
}
