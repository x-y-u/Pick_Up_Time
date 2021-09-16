package com.example.mytest.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.mytest.ImageActivity;
import com.example.mytest.R;

import java.util.List;

public class PushImageRecyclerViewAdapter2 extends RecyclerView.Adapter {

    private Context mContext;
    private List<Integer> images;
    private List<String> image_ids;
    public PushImageRecyclerViewAdapter2(Context mcontext, List<Integer> images){
        this.mContext = mcontext;
        this.images = images;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.push_image_item2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).imageView.setAdjustViewBounds(true);
//        Glide.with(mContext).load(images.get(position))
//                .placeholder(R.drawable.on_loading)
//                .into(((MyViewHolder)holder).imageView);
        ((MyViewHolder)holder).imageView.setImageResource(images.get(position));
        ((MyViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageActivity.class);
                intent.putExtra("imageurl",images.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.push_item2_iv);
        }
    }

    public void setImages(List<Integer> images){
        this.images = images;
        notifyDataSetChanged();
    }

    public void setImage_ids(List<String> image_ids){
        this.image_ids = image_ids;
    }
}
