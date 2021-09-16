package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CircleImageView;
import com.example.mytest.R;
import com.example.mytest.db.CommentBean;
import com.example.mytest.db.PostBean;

import java.util.List;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<PostBean> postBeans;
    private OnCommentListener onCommentListener;

    public void setOnCommentListener(OnCommentListener onCommentListener){
        this.onCommentListener = onCommentListener;
    }

    public PostRecyclerViewAdapter(Context mcontext, List<PostBean> postBeans){
        this.mcontext = mcontext;
        this.postBeans = postBeans;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new MyViewHolder1(LayoutInflater.from(mcontext).inflate(R.layout.layout_post_list_item,parent,false));
        }
        return new MyViewHolder2(LayoutInflater.from(mcontext).inflate(R.layout.layout_empty_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==1){
            PostBean postBean = postBeans.get(position);
            ((MyViewHolder1)holder).tv_username.setText(postBean.getUsername());
            ((MyViewHolder1)holder).tv_agree_num.setText(postBean.getAgrees()+"");
            ((MyViewHolder1)holder).tv_comment_num.setText(postBean.getComments()+"");
            ((MyViewHolder1)holder).tv_content.setText(postBean.getWords());
            Glide.with(mcontext).load(postBean.getHead_image())
                    .into(((MyViewHolder1)holder).head_image);
            Glide.with(mcontext).load(postBean.getFirst_img())
                    .placeholder(mcontext.getResources().getDrawable(R.drawable.on_loading))
                    .into(((MyViewHolder1)holder).first_img);
            ((MyViewHolder1)holder).it_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCommentListener!=null){
                        onCommentListener.OnComment(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Math.max(1,postBeans.size());
    }

    @Override
    public int getItemViewType(int position){
        if (postBeans.isEmpty()){
            return 2;
        }
        return 1;
    }

    private class MyViewHolder1 extends RecyclerView.ViewHolder{
        private TextView tv_username,tv_content,tv_agree_num,tv_comment_num;
        private ImageView first_img;
        private CircleImageView head_image;
        private ImageButton it_agree,it_comment;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.post_item_username);
            tv_content = itemView.findViewById(R.id.post_item_content);
            tv_agree_num = itemView.findViewById(R.id.post_item_agree_num);
            tv_comment_num = itemView.findViewById(R.id.post_item_comment_num);
            first_img = itemView.findViewById(R.id.post_item_first_img);
            head_image = itemView.findViewById(R.id.post_item_head_image);
            it_agree = itemView.findViewById(R.id.post_item_agree);
            it_comment = itemView.findViewById(R.id.post_item_comment);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder{

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setPost(List<PostBean> postBeans){
        this.postBeans = postBeans;
        notifyDataSetChanged();
    }

    public void add_comment(int pos){
        postBeans.get(pos).setComments(postBeans.get(pos).getComments()+1);
        notifyDataSetChanged();
    }

    public interface OnCommentListener{
        public void OnComment(int pos);
    }
}
