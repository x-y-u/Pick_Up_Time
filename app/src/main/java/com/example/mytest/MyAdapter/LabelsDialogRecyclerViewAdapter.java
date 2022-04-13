package com.example.mytest.MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.R;

import java.util.List;

public class LabelsDialogRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<String> labels;
    private Context mcontext;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public LabelsDialogRecyclerViewAdapter(Context mcontext, List<String> labels){
        this.mcontext = mcontext;
        this.labels = labels;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.labels_bottom_item,parent,false));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.labels_bottom_tv);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ((MyViewHolder)holder).textView.setText(labels.get(position));
        ((MyViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    public interface OnItemClickListener{
        public void OnClick(int position);
    }
}
