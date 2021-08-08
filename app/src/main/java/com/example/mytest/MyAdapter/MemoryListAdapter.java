package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mytest.Common.MyGridView;
import com.example.mytest.R;
import com.example.mytest.db.MemoryBean;

import java.util.List;

public class MemoryListAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater mlayoutinflater;
    private List<MemoryBean> memoryBeans;
    private char c =47;//特殊字符'/'

    private MemoryListAdapter.ViewHolder holder = null;
    private int width;

    public MemoryListAdapter(Context mcontext, List<MemoryBean> memoryBeans) {
        this.mcontext = mcontext;
        this.mlayoutinflater = LayoutInflater.from(mcontext);
        this.memoryBeans = memoryBeans;
    }

    public void SetTime(int year,int month){
        for (MemoryBean memorybean:memoryBeans
             ) {
            memorybean.setYear(year);
            memorybean.setMonth(month);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return memoryBeans.size();
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
        public TextView day;
        public TextView monthandweekday;
        public TextView words;
        public MyGridView gridView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.layout_memory_item,null);
            holder = new ViewHolder();
            holder.day = convertView.findViewById(R.id.memory_item_day);
            holder.monthandweekday = convertView.findViewById(R.id.memory_item_monthandweekdays);
            holder.words = convertView.findViewById(R.id.memory_words);
            holder.gridView = convertView.findViewById(R.id.memory_item_image_grid);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.day.setText(""+memoryBeans.get(position).getDay());
        holder.monthandweekday.setText(memoryBeans.get(position).getMonth()+""+c+""+memoryBeans.get(position).getWeekday());
        holder.words.setText(memoryBeans.get(position).getWords());
        int numcolounm = memoryBeans.get(position).getImages().size()>3?3:memoryBeans.get(position).getImages().size();
        holder.gridView.setNumColumns(numcolounm);

        holder.gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (width <= 0 ){
                    width = holder.gridView.getMeasuredWidth();
                    notifyDataSetChanged();
                }
            }
        });
        holder.gridView.setAdapter(new GridImageAdapter(mcontext,memoryBeans.get(position).getImages(),3,width));
        return convertView;
    }
}
