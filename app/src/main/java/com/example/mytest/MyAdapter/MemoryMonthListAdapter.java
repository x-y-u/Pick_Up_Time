package com.example.mytest.MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mytest.R;

public class MemoryMonthListAdapter extends BaseAdapter {
    private MemoryMonthListAdapter.ViewHolder holder = null;
    private Context mcontext;
    private int maxmonth;
    private LayoutInflater mlayoutinflater;
    public MemoryMonthListAdapter(Context mcontext,int maxmonth){
        this.maxmonth = maxmonth;
        this.mcontext = mcontext;
        this.mlayoutinflater = LayoutInflater.from(mcontext);
    }
    @Override
    public int getCount() {
        return maxmonth;
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
        TextView textView;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.text_item,null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.item_textview);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        int month = maxmonth - position;
        holder.textView.setText(month + "月");
        holder.textView.setTextSize(17);
        holder.textView.setTextColor(R.color.darkgray);
        return convertView;
    }
}
