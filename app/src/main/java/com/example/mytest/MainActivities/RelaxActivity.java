package com.example.mytest.MainActivities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.ImageButton;

import com.example.mytest.Common.BaseStatusBarActivity;
import com.example.mytest.games.GameFourActivity;
import com.example.mytest.games.GameOneActivity;
import com.example.mytest.games.GameThreeActivity;
import com.example.mytest.games.GameTwoActivity;
import com.example.mytest.R;
import com.example.mytest.xytest.TestActivity;


public class RelaxActivity extends BaseStatusBarActivity implements View.OnClickListener {
    private CardView[] cardViews= new CardView[4];
    private ImageButton back;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_relax);
        InitViews();
        InitDatas();
        InitEvents();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void InitEvents() {
        for (int i=0;i<cardViews.length;i++){
            cardViews[i].setOnClickListener(this);
        }
        back.setOnClickListener(this);
    }

    private void InitDatas() {
    }

    private void InitViews() {
        cardViews[0] = findViewById(R.id.card_game1);
        cardViews[1] = findViewById(R.id.card_game2);
        cardViews[2] = findViewById(R.id.card_game3);
        cardViews[3] = findViewById(R.id.card_game4);
        back = findViewById(R.id.relax_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_game1:
                startActivity(new Intent(RelaxActivity.this, GameOneActivity.class));
                break;
            case R.id.card_game2:
                startActivity(new Intent(RelaxActivity.this, GameTwoActivity.class));
                break;
            case R.id.card_game3:
                startActivity(new Intent(RelaxActivity.this, GameThreeActivity.class));
                break;
            case R.id.card_game4:
                startActivity(new Intent(RelaxActivity.this, GameFourActivity.class));
                break;
            case R.id.relax_back:
                finish();
                break;
            default:
                break;
        }
    }
}