package com.example.mytest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Dialogs.NoteBottomDialog;

public class NoteEditingActivity extends BaseStatusBarActivity {
    private ImageView img_back,img_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editing);
        InitViews();
        InitEvents();
    }

    private void InitEvents() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteBottomDialog dialog = NoteBottomDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"NoteEditing");
            }
        });
    }

    private void InitViews() {
        img_back = findViewById(R.id.note_editing_back);
        img_more = findViewById(R.id.note_editing_more);
    }
}