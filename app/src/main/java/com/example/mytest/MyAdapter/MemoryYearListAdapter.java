package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mytest.Common.MyListView;
import com.example.mytest.R;

import java.util.List;

public class MemoryYearListAdapter extends BaseAdapter {
    private int startyear,endyear;
    private Context mcontext;
    private LayoutInflater mlayoutinflater;
    private MemoryYearListAdapter.ViewHolder holder = null;
    public MemoryYearListAdapter(Context mcontext,int startyear,int endyear){
        this.mcontext = mcontext;
        this.startyear = startyear;
        this.endyear = endyear;
        this.mlayoutinflater = LayoutInflater.from(mcontext);
    }

    public interface OnItemClickListener{
        public void OnItemClick(int year,int month);
    }
    //设置回调事件，让用户可以通过选择日期修改页面展示内容
    public OnItemClickListener onItemClickListener;

    public void SetOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return endyear - startyear + 1;
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
        MyListView listView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.layout_memory_time_item,null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.memory_year);
            holder.listView = convertView.findViewById(R.id.memory_month_list);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        int year = endyear - position;
        holder.textView.setText("-" + year + "年");
        holder.listView.setAdapter(new MemoryMonthListAdapter(mcontext,12));
        holder.listView.setVerticalScrollBarEnabled(false);
        holder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.OnItemClick(year,12 - position);
            }
        });
        return convertView;
    }
}
