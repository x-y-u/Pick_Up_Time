package com.example.mytest.MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class UploadNineGridRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context mcontext;
    private List<String> images;
    private OnItemClickListener onItemClickListener;
    //private int width;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public UploadNineGridRecyclerViewAdapter(Context mconext){
        this.mcontext = mconext;
        this.images = new ArrayList<>();
        //this.width = width/3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.nine_grid_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((MyViewHolder)holder).img.setImageResource(images.get(position));
        if (position == images.size()){
            ((MyViewHolder)holder).img.setImageResource(R.drawable.image_add);
        }else {
            Glide.with(mcontext).load(images.get(position)).into(((MyViewHolder)holder).img);
        }
        ((MyViewHolder)holder).img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnClick(position);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView card;
        public ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_item_iv);
            card = (CardView) itemView;
        }
    }

    public interface OnItemClickListener{
        public void OnClick(int pos);
    }

    @Override
    public int getItemCount() {
        return Math.min(images.size()+1, 9);
    }

    public void setImages(int index,String new_path) {
        images.remove(index);
        images.add(index,new_path);
        notifyDataSetChanged();
    }
    public void addImages(List<String> new_images){
        for(int i = 0;i<new_images.size();i++){
            images.add(images.size(),new_images.get(i));
        }
        notifyDataSetChanged();
    }

    public int getImagesNum(){
        return images.size();
    }

    public List<String> getImages(){
        return images;
    }
}
