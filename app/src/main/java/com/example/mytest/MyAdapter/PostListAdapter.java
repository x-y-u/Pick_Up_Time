package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mytest.Common.CircleImageView;
import com.example.mytest.Common.MyGridView;
import com.example.mytest.R;
import com.example.mytest.db.PostBean;

import java.util.List;

public class PostListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mlayoutinflater;
    private List<PostBean> postBeans;

    private PostListAdapter.ViewHolder holder = null;
    private int width;

    public PostListAdapter(Context mcontext, List<PostBean> postBeans) {
        this.mcontext = mcontext;
        this.mlayoutinflater = LayoutInflater.from(mcontext);
        this.postBeans = postBeans;
    }

    @Override
    public int getCount() {
        return postBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public CircleImageView head_image;
        public TextView username;
        public TextView time;
        public TextView words;
        public MyGridView gridView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.layout_post_item,null);
            holder = new PostListAdapter.ViewHolder();
            holder.head_image = convertView.findViewById(R.id.post_head_image);
            holder.username = convertView.findViewById(R.id.post_username);
            holder.time =convertView.findViewById(R.id.post_time);
            holder.words = convertView.findViewById(R.id.post_words);
            holder.gridView = convertView.findViewById(R.id.post_item_image_grid);
            convertView.setTag(holder);
        }else {
            holder = (PostListAdapter.ViewHolder) convertView.getTag();
        }
        holder.head_image.setImageResource(postBeans.get(position).getHead_image());
        holder.words.setText(postBeans.get(position).getWords());
        holder.username.setText(postBeans.get(position).getUsername());
        holder.time.setText("今天" + postBeans.get(position).getHour() + ":" + postBeans.get(position).getMinute());
        int numcolounm = postBeans.get(position).getImages().size()>3?3:postBeans.get(position).getImages().size();
        holder.gridView.setNumColumns(numcolounm);

        holder.gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(width <= 0){
                    width = holder.gridView.getMeasuredWidth();
                    notifyDataSetChanged();
                }
            }
        });
        holder.gridView.setAdapter(new GridImageAdapter(mcontext,postBeans.get(position).getImages(),9,width));
        return convertView;
    }
}
