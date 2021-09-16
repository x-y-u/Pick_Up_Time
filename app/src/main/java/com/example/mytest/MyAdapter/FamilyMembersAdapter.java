package com.example.mytest.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CircleImageView;
import com.example.mytest.Common.CommonUtils;
import com.example.mytest.Common.HttpUtils;
import com.example.mytest.PersonalSpaceActivity;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FamilyMembersAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<String> images;
    private List<String> relationships;
    private List<String> familyids;
    private List<MemoryBean> memoryBeans;
    private String s;
    public FamilyMembersAdapter(Context mcontext,List<String> images,List<String> relationships,List<String> familyids){
        this.mcontext = mcontext;
        this.images = images;
        this.relationships = relationships;
        this.familyids = familyids;
        this.memoryBeans = new ArrayList<>();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.family_member_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).imageView.setAdjustViewBounds(true);
        if (position<images.size()){
            Glide.with(mcontext).load(images.get(position)).into(((MyViewHolder)holder).imageView);
            ((MyViewHolder)holder).textView.setText(relationships.get(position));
            ((MyViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getMemory(position);
                }
            });
        }else {
            ((MyViewHolder)holder).imageView.setImageResource(R.mipmap.family_add);
            ((MyViewHolder)holder).textView.setText("添加家人");
        }
    }

    @Override
    public int getItemCount() {
        return images.size()+1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView imageView;
        public TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.member_head_image);
            textView = itemView.findViewById(R.id.member_relationship);
        }
    }

    private void getMemory(int position){
        new Thread(new Runnable(){
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                String url = "http://120.27.195.193:8080/queryMemoryInfoByID";
                try {
                    String userid = familyids.get(position);
                    json.put("userid",userid);
                    s = HttpUtils.post(url,json.toString());
                    JSONArray jsonArray = new JSONArray(s);
                    memoryBeans.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        //Log.i("jsonArray",jsonArray.getJSONObject(i).get("url").toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String weekday = CommonUtils.num_to_weekday(jsonObject.getInt("weekday"));
                        memoryBeans.add(new MemoryBean(userid,jsonObject.getInt("year"),jsonObject.getInt("month"),
                                jsonObject.getInt("day"),jsonObject.getString("title"),jsonObject.getString("passage"),
                                jsonObject.getString("main_imageurl"),weekday));
                    }
                    Intent intent = new Intent(mcontext, PersonalSpaceActivity.class);
                    intent.putExtra("imageurl",images.get(position));
                    intent.putExtra("relationship",relationships.get(position));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("memoryBeans", (Serializable) memoryBeans);
                    intent.putExtras(bundle);
                    mcontext.startActivity(intent);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
