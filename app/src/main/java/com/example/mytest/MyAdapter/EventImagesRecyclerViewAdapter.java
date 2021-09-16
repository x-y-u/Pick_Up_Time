package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.R;

import java.util.List;

public class EventImagesRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<String> images;
    public EventImagesRecyclerViewAdapter(Context mcontext){
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.event_images_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(mcontext)
                .load(images.get(position))
                .placeholder(mcontext.getResources().getDrawable(R.drawable.on_loading))
                .into(((MyViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        if (images==null){
            return 0;
        }
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.event_images_item_iv);
        }
    }

    public void loading(List<String> images){
        this.images = images;
        notifyDataSetChanged();
    }
}
