package com.example.mytest.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.NoteEditingActivity;
import com.example.mytest.R;
import com.example.mytest.db.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<NoteBean> noteBeans;
    private List<Integer> selected_states;
    private Context mcontext;
    private OnClickListener onClickListener;
    private int state;//为1时可选，为0时不可选
    private int num;

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public NotesRecyclerViewAdapter(Context mcontext,List<NoteBean> noteBeans){
        this.mcontext = mcontext;
        this.noteBeans = noteBeans;
        this.selected_states = new ArrayList<>();
        for (int i=0;i<noteBeans.size();i++){
            selected_states.add(0);
        }
        this.state = 0;
        this.num = 0;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.notes_item,parent,false));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView is_selected;
        private TextView title,content,time;
        private RelativeLayout a_note;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.note_title);
            this.content = itemView.findViewById(R.id.note_content);
            this.time = itemView.findViewById(R.id.note_time);
            this.is_selected = itemView.findViewById(R.id.is_selected);
            this.a_note = itemView.findViewById(R.id.a_note);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((MyViewHolder)holder).is_selected
        if (selected_states.get(position) == 0){
            ((MyViewHolder)holder).is_selected.setImageResource(R.mipmap.note_unselected);
        }else {
            ((MyViewHolder)holder).is_selected.setImageResource(R.mipmap.note_selected);
        }
        if (state == 0){
            ((MyViewHolder)holder).is_selected.setVisibility(View.INVISIBLE);
        }else {
            ((MyViewHolder)holder).is_selected.setVisibility(View.VISIBLE);
        }
        ((MyViewHolder)holder).a_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 1){
                    if (selected_states.get(position) == 0){
                        selected_states.set(position,1);
                        num++;
                    }else {
                        selected_states.set(position,0);
                        num--;
                    }
                    notifyDataSetChanged();
                    onClickListener.OnClick(num);
                }else {
                    Intent intent = new Intent(mcontext, NoteEditingActivity.class);
                    mcontext.startActivity(intent);
                }
            }
        });
        ((MyViewHolder)holder).a_note.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (state == 0){
                    state = 1;
                    selected_states.set(position,1);
                    num = 1;
                    notifyDataSetChanged();
                    onClickListener.OnLongClick();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteBeans.size();
    }

    public void setState(int state) {
        this.state = state;
    }

    public void reset(){
        for (int i=0;i<selected_states.size();i++){
            selected_states.set(i,0);
        }
        num = 0;
        notifyDataSetChanged();
    }

    public void all_check(){
        for (int i = 0;i<selected_states.size();i++){
            selected_states.set(i,1);
        }
        notifyDataSetChanged();
    }

    public interface OnClickListener{
        public void OnClick(int num);
        public void OnLongClick();
    }
}
