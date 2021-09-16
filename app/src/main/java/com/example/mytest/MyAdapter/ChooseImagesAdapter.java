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

import java.util.ArrayList;
import java.util.List;

public class ChooseImagesAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<String> images;
    private List<Boolean> is_selected;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public ChooseImagesAdapter(Context mcontext,List<String> images){
        this.mcontext = mcontext;
        this.images = images;
        is_selected = new ArrayList<>();
        for (int i = 0;i<images.size();i++){
            is_selected.add(false);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.images_choose_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (is_selected.get(position)){
            ((MyViewHolder)holder).is_selected.setImageResource(R.mipmap.note_selected);
        }else {
            ((MyViewHolder)holder).is_selected.setImageResource(R.mipmap.note_unselected);
        }
        Glide.with(mcontext).load(images.get(position)).into(((MyViewHolder)holder).img);
        ((MyViewHolder)holder).img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnClick(position);
                notifyDataSetChanged();
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img,is_selected;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.images_choose_iv);
            is_selected = itemView.findViewById(R.id.images_choose_is_selected);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnClickListener{
        public void OnClick(int pos);
    }

    public int changeState(int pos){
        if (is_selected.get(pos)){
            is_selected.set(pos,false);
            return -1;
        }else {
            is_selected.set(pos,true);
            return 1;
        }
    }
}
