package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import java.util.List;

public class MemoryRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context mcontext;
    private List<MemoryBean> memoryBeans;
    private int[] voices = new int[6];
    private int[] peoples = new int[3];

    public MemoryRecyclerViewAdapter(Context mcontext,List<MemoryBean> memoryBeans){
        this.mcontext = mcontext;
        this.memoryBeans = memoryBeans;
        voices[0] = R.mipmap.memory_voice1;
        voices[1] = R.mipmap.memory_voice2;
        voices[2] = R.mipmap.memory_voice3;
        voices[3] = R.mipmap.memory_voice4;
        voices[4] = R.mipmap.memory_voice5;
        voices[5] = R.mipmap.memory_voice6;
        peoples[0] = R.mipmap.memory_people1;
        peoples[1] = R.mipmap.memory_people2;
        peoples[2] = R.mipmap.memory_people3;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new MyViewHolder1(LayoutInflater.from(mcontext).inflate(R.layout.layout_memory_list_item,parent,false));
        }
        return new MyViewHolder2(LayoutInflater.from(mcontext).inflate(R.layout.layout_empty_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==1){
            MemoryBean memoryBean = memoryBeans.get(position);
            Glide.with(mcontext).load(memoryBean.getImageurl())
                    .placeholder(mcontext.getResources().getDrawable(R.drawable.on_loading))
                    .into(((MyViewHolder1)holder).imageView);
            ((MyViewHolder1)holder).imageView.setAdjustViewBounds(true);
            ((MyViewHolder1)holder).tv_title.setText(memoryBean.getTitle());
            ((MyViewHolder1)holder).tv_day.setText(memoryBean.getDay()+"");
            ((MyViewHolder1)holder).tv_month.setText(memoryBean.getMonth()+"月");
            ((MyViewHolder1)holder).tv_year.setText(memoryBean.getYear()+"");
            ((MyViewHolder1)holder).iv_voice.setImageResource(voices[position%6]);
            ((MyViewHolder1)holder).iv_people.setImageResource(peoples[position%3]);
        }
    }

    @Override
    public int getItemCount() {
        //return memoryBeans.size();
        return Math.max(1,memoryBeans.size());
    }

    @Override
    public int getItemViewType(int position){
        if (memoryBeans.isEmpty()){
            return 2;
        }
        return 1;
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{
        public ImageView imageView,iv_voice,iv_people;
        public TextView tv_day,tv_month,tv_title,tv_year;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.memory_first_img);
            this.tv_day = itemView.findViewById(R.id.memory_item_day);
            this.tv_month = itemView.findViewById(R.id.memory_item_month);
            this.tv_year = itemView.findViewById(R.id.memory_item_year);
            this.tv_title = itemView.findViewById(R.id.memory_item_title);
            this.iv_voice = itemView.findViewById(R.id.memory_item_voice_iv);
            this.iv_people = itemView.findViewById(R.id.memory_item_people_iv);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder{

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setMemory(List<MemoryBean> memory){
        this.memoryBeans = memory;
        notifyDataSetChanged();
    }

    public void addMemory(MemoryBean memoryBean){
        this.memoryBeans.add(memoryBean);
        notifyDataSetChanged();
    }
}
