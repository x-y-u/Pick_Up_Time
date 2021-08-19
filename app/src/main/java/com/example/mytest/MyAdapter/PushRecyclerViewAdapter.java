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
import com.example.mytest.ImageActivity;
import com.example.mytest.R;

import java.util.List;

public class PushRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<String> images;
    private Context mContext;
//    private OnClickListener onClickListener;
//    public void setOnClickListener(OnClickListener onClickListener){
//        this.onClickListener = onClickListener;
//    }
    public PushRecyclerViewAdapter(Context mContext,List<String> images){
        this.mContext = mContext;
        this.images = images;
    }

    @NonNull
    @Override
    public PushRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.push_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).imageView.setAdjustViewBounds(true);
       Glide.with(mContext).load(images.get(position)).into(((MyViewHolder)holder).imageView);
        ((MyViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (onClickListener != null){
//                    onClickListener.OnClick(position);
//                }
                Intent intent = new Intent(mContext, ImageActivity.class);
                intent.putExtra("imageurl",images.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.push_item_iv);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

//    public interface OnClickListener{
//        public void OnClick(int position);
//    }
}
