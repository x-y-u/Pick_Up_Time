package com.example.mytest.MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mytest.R;

import java.util.List;

public class GridImageAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mlayoutinflater;
    private List<Integer> images;
    private int mmaxsize;
    private int col;
    private int width;
    private int height;

    public GridImageAdapter(Context mcontext, List<Integer> images,int mmaxsize,int width) {
        this.mcontext = mcontext;
        this.mlayoutinflater = LayoutInflater.from(mcontext);
        this.images = images;
        this.mmaxsize = mmaxsize;
        col = images.size()<3?images.size():3;
        this.width = width/col;
        this.height = this.width;
    }

    @Override
    public int getCount() {
        return images.size()>mmaxsize?mmaxsize:images.size();
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
        public ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.image_grid_item,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        holder.imageView.setImageResource(images.get(position));
        return convertView;
    }
}
