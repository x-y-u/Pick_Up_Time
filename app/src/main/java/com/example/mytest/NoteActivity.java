package com.example.mytest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.MyAdapter.NotesRecyclerViewAdapter;
import com.example.mytest.db.NoteBean;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends BaseStatusBarActivity {
    private List<NoteBean> noteBeans;
    private RecyclerView notes_rv;
    private NotesRecyclerViewAdapter adapter;
    private FloatingActionButton note_add_bt;
    private TextView note_top_title,note_cancel,selected_num;
    private ImageButton make_note_bt,note_delete;
    private RelativeLayout note_bottom;
    private CheckBox is_all_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        note_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteActivity.this,Make_NoteActivity.class));
            }
        });
        adapter.setOnClickListener(new NotesRecyclerViewAdapter.OnClickListener() {
            @Override
            public void OnClick(int num) {
                selected_num.setText(num+"");
                if (num == noteBeans.size()){
                    is_all_selected.setChecked(true);
                }
            }

            @Override
            public void OnLongClick() {
                note_setstate(1);
                selected_num.setText("1");
            }
        });
        note_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setState(0);
                note_setstate(0);
                adapter.reset();
            }
        });
        is_all_selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    adapter.all_check();
                    selected_num.setText(noteBeans.size()+"");
                }else {
                    adapter.reset();
                    selected_num.setText("0");
                }
            }
        });
    }

    private void note_setstate(int state) {//为0表示切换原视图，为1表示切换批量编辑视图
        if (state == 1){
            note_top_title.setText("批量编辑");
            make_note_bt.setVisibility(View.INVISIBLE);
            note_add_bt.setVisibility(View.INVISIBLE);
            note_cancel.setVisibility(View.VISIBLE);
            note_bottom.setVisibility(View.VISIBLE);
            selected_num.setText("0");
        }else {
            note_top_title.setText("笔记");
            make_note_bt.setVisibility(View.VISIBLE);
            note_add_bt.setVisibility(View.VISIBLE);
            note_cancel.setVisibility(View.INVISIBLE);
            note_bottom.setVisibility(View.INVISIBLE);
        }
    }

    private void InitDatas() {
        noteBeans = new ArrayList<>();
        for (int i=0;i<15;i++){
            noteBeans.add(new NoteBean("111","aaaaa","aaaaa",8,10));
        }
        adapter = new NotesRecyclerViewAdapter(this,noteBeans);
        notes_rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        notes_rv.setAdapter(adapter);
        selected_num.setText("0");
    }

    private void InitViews() {
        notes_rv = findViewById(R.id.notes_rv);
        note_add_bt = findViewById(R.id.note_add_bt);
        selected_num = findViewById(R.id.selected_num);
        note_top_title = findViewById(R.id.note_top_title);
        note_cancel = findViewById(R.id.note_cancel);
        make_note_bt = findViewById(R.id.make_note_bt);
        note_delete = findViewById(R.id.note_delete);
        note_bottom = findViewById(R.id.note_bottom);
        is_all_selected = findViewById(R.id.is_all_selected);
    }

//    @Override
//    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
//        return getApplicationContext().registerReceiver(receiver,filter);
//        //return super.registerReceiver(receiver, filter);
//    }
//
//    @Override
//    public void unregisterReceiver(BroadcastReceiver receiver) {
//        getApplicationContext().unregisterReceiver(receiver);
//        //super.unregisterReceiver(receiver);
//    }
}