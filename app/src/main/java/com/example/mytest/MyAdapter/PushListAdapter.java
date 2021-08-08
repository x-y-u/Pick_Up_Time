package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytest.R;

public class PushListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mlayoutinflater;
    private PushListAdapter.ViewHolder viewHolder;
    public PushListAdapter(Context context){
        this.context = context;
        mlayoutinflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView category;
        public TextView time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.push_list_item,null);
            viewHolder = new PushListAdapter.ViewHolder();
            viewHolder.img = convertView.findViewById(R.id.news_img);
            viewHolder.time = convertView.findViewById(R.id.news_time);
            viewHolder.title = convertView.findViewById(R.id.news_title);
            viewHolder.category = convertView.findViewById(R.id.news_category);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
