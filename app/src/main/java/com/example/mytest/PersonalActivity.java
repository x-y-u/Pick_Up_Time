package com.example.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.Dialogs.SignInDialog;

public class PersonalActivity extends BaseStatusBarActivity {
    private ImageButton btn_back;
    private ImageView personal_notes,personal_sign_in;
    private RelativeLayout to_personal_score_mall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        btn_back = findViewById(R.id.personal_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        to_personal_score_mall = findViewById(R.id.personal_to_score_mall);
        to_personal_score_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalActivity.this,ScoreMallActivity.class));
            }
        });
        personal_notes = findViewById(R.id.personal_notes);
        personal_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalActivity.this,NoteActivity.class));
            }
        });
        personal_sign_in = findViewById(R.id.personal_sign_in);
        personal_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInDialog dialog = SignInDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"sign_in");
            }
        });
    }
}