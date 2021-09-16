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

import java.util.List;

public class EventMoviesRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<String> images,names;

    public EventMoviesRecyclerViewAdapter(Context mcontext){
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.event_movies_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(mcontext).load(images.get(position))
                .placeholder(mcontext.getResources().getDrawable(R.drawable.on_loading))
                .into(((MyViewHolder)holder).imageView);
        ((MyViewHolder)holder).textView.setText("《"+names.get(position)+"》");
    }

    @Override
    public int getItemCount() {
        if (images!=null){
            return images.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.event_movies_item_iv);
            textView = itemView.findViewById(R.id.event_movies_item_tv);
        }
    }

    public void loading(List<String> images,List<String> names){
        this.images = images;
        this.names = names;
        notifyDataSetChanged();
    }
}
