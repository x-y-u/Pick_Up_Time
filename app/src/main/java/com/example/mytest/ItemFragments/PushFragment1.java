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
import com.example.mytest.MyAdapter.PushImageRecyclerViewAdapter2;
import com.example.mytest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PushFragment1 extends Fragment {

    private View view;
    private List<Integer> images;
    private List<String> image_ids;
    private PushImageRecyclerViewAdapter2 adapter;
    private String s;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.push_frag1,container,false);
        InitViews();
        InitDatas();
        //getImages();
        return view;
    }

    private void InitDatas() {
        image_ids = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.push_1_3);
        images.add(R.drawable.push_1_4);
        images.add(R.drawable.push_1_5);
        images.add(R.drawable.push_1_6);
        images.add(R.drawable.push_1_7);
        images.add(R.drawable.push_1_8);
        images.add(R.drawable.push_1_9);
        images.add(R.drawable.push_1_10);
        images.add(R.drawable.push_1_11);
        images.add(R.drawable.push_1_12);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new PushImageRecyclerViewAdapter2(getContext(),images);
        recyclerView.setAdapter(adapter);
    }

    private void InitViews() {
        recyclerView = view.findViewById(R.id.push_frag1_rv);
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
