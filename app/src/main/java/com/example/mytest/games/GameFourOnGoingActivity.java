package com.example.mytest.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mytest.ChooseImageActivity;
import com.example.mytest.Dialogs.LoadingDialog;
import com.example.mytest.HtmlActivity;
import com.example.mytest.MakeAlbumActivity;
import com.example.mytest.R;

import java.util.List;

public class GameFourOnGoingActivity extends AppCompatActivity {
    private ImageView before,after;
    private TextView tv_choose;
    private CardView change;
    private int request_code = 13;
    private Boolean is_empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_four_on_going);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        tv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameFourOnGoingActivity.this, ChooseImageActivity.class);
                intent.putExtra("maxsize",1);
                startActivityForResult(intent,request_code);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!is_empty){
                    LoadingDialog dialog = LoadingDialog.newInstance();
                    dialog.show(getSupportFragmentManager(),"loading");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            after.setImageResource(R.drawable.after_change);
                        }
                    },1000);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1){
            List<String> images = data.getStringArrayListExtra("images");
            Glide.with(this).load(R.drawable.before_change).into(before);
            is_empty = false;
            tv_choose.setVisibility(View.INVISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void InitDatas() {
        is_empty = true;
    }

    private void InitViews() {
        before = findViewById(R.id.game4_before_change_iv);
        after = findViewById(R.id.game4_after_change_iv);
        change = findViewById(R.id.game4_change_card);
        tv_choose = findViewById(R.id.game4_ongoing_choose);
    }
}