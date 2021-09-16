package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.Common.CircleImageView;
import com.example.mytest.R;
import com.example.mytest.db.CommentBean;

import java.util.List;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<CommentBean> commentBeans;

    public CommentRecyclerViewAdapter(Context mcontext,List<CommentBean> commentBeans){
        this.mcontext = mcontext;
        this.commentBeans = commentBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommentBean commentBean = commentBeans.get(position);
        ((MyViewHolder)holder).head_image.setImageResource(commentBean.getHead_image());
        ((MyViewHolder)holder).username.setText(commentBean.getUsername());
        ((MyViewHolder)holder).content.setText(commentBean.getContent());
        ((MyViewHolder)holder).time.setText(commentBean.getTime());
    }

    @Override
    public int getItemCount() {
        return commentBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView head_image;
        private TextView username,content,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            head_image = itemView.findViewById(R.id.comment_item_head_image);
            username = itemView.findViewById(R.id.comment_item_username);
            content = itemView.findViewById(R.id.comment_item_content);
            time = itemView.findViewById(R.id.comment_item_time);
        }
    }

    public void add_comment(CommentBean commentBean){
        commentBeans.add(0,commentBean);
        notifyDataSetChanged();
    }
}
