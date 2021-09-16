package com.example.mytest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.R;

import java.util.List;

public class LabelsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<String> labels;
    private Context mcontext;
    private int selectedpos;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public LabelsRecyclerViewAdapter(Context mcontext,List<String> labels){
        this.mcontext = mcontext;
        this.labels = labels;
        selectedpos = 0;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.labels_item,parent,false));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.label_tv);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).textView.setText(labels.get(position));
        if (position != selectedpos){
            ((MyViewHolder)holder).textView.setTextColor(mcontext.getResources().getColor(R.color.label_unselected));
            ((MyViewHolder)holder).textView.setBackground(mcontext.getResources().getDrawable(R.drawable.text_round_gray_bg));
        }else {
            ((MyViewHolder)holder).textView.setTextColor(mcontext.getResources().getColor(R.color.label_selected));
            ((MyViewHolder)holder).textView.setBackground(mcontext.getResources().getDrawable(R.drawable.text_round_indianred_bg));
        }
        ((MyViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position!=selectedpos){
                    if (onItemClickListener!=null){
                        onItemClickListener.OnClick(labels.get(position));
                    }
                    selectedpos = position;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    public interface OnItemClickListener{
        public void OnClick(String label_text);
    }
}
