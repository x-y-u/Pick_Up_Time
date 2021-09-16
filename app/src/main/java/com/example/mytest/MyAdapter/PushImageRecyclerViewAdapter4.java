package com.example.mytest.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class PushImageRecyclerViewAdapter4 extends RecyclerView.Adapter {
    private List<Integer> images;
    private List<String> image_ids;
    private List<Integer> is_selected;
    private Boolean state;
    private Context mContext;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public PushImageRecyclerViewAdapter4(Context mContext, List<Integer> images){
        this.mContext = mContext;
        this.images = images;
        is_selected = new ArrayList<>();
        for (int i=0;i<images.size();i++){
            is_selected.add(0);
        }
        state = false;
    }

    @NonNull
    @Override
    public PushImageRecyclerViewAdapter4.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.push_image_item4,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).imageView.setAdjustViewBounds(true);
        ((MyViewHolder)holder).imageView.setImageResource(images.get(position));
        ((MyViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state){
                    if (is_selected.get(position) == 1){
                        is_selected.set(position,0);
                        ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_unselected);
                    }else {
                        is_selected.set(position,1);
                        ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_selected);
                    }
                }
            }
        });
        ((MyViewHolder)holder).imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener!=null){
                    listener.OnLongClick();
                    is_selected.set(position,1);
                    notifyDataSetChanged();
                    //((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_selected);
                    return true;
                }
                return false;
            }
        });
        if (state){
            ((MyViewHolder)holder).iv_is_selected.setVisibility(View.VISIBLE);
        }else {
            ((MyViewHolder)holder).iv_is_selected.setVisibility(View.INVISIBLE);
        }
        if (is_selected.get(position) == 1){
            ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_selected);
        }else {
            ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_unselected);
        }
        ((MyViewHolder)holder).iv_is_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_selected.get(position) == 1){
                    is_selected.set(position,0);
                    ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_unselected);
                }else {
                    is_selected.set(position,1);
                    ((MyViewHolder)holder).iv_is_selected.setImageResource(R.mipmap.image_selected);
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private ImageView iv_is_selected;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.push_item4_iv);
            iv_is_selected = itemView.findViewById(R.id.push_search_result_is_selected);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setImages(List<Integer> images){
        this.images = images;
        notifyDataSetChanged();
    }

    public void setState(Boolean state){
        if (state){
            reset();
        }
        this.state = state;
    }

    public void reset(){
        for (int i=0;i<is_selected.size();i++){
            is_selected.set(i,0);
        }
    }

    public interface OnItemClickListener{
        public void OnClick();
        public void OnLongClick();
    }
}
