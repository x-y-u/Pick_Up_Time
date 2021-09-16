package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mytest.Dialogs.GameThreeOverDialog;
import com.example.mytest.ItemFragments.GameThreeOnGoingFragment;
import com.example.mytest.ItemFragments.GameThreeStartFragment;

public class GameThreeActivity extends AppCompatActivity {
    private ImageView back;
    private GameThreeStartFragment gameThreeStartFragment;
    private GameThreeOnGoingFragment gameThreeOnGoingFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        InitViews();
        InitDatas();
        InitEvents();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.game3_content,gameThreeStartFragment);
        transaction.commit();
    }

    private void InitViews() {
        back = findViewById(R.id.game_three_back);
    }

    private void InitDatas(){
        fragmentManager = getSupportFragmentManager();
        gameThreeStartFragment = new GameThreeStartFragment();
        gameThreeOnGoingFragment = new GameThreeOnGoingFragment();
    }

    private void InitEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gameThreeStartFragment.setOnStartListener(new GameThreeStartFragment.OnStartListener() {
            @Override
            public void on_start() {
                // 开启一个Fragment事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(gameThreeStartFragment);
                transaction.add(R.id.game3_content,gameThreeOnGoingFragment);
                transaction.commit();
            }
        });
        gameThreeOnGoingFragment.setOnFinishListener(new GameThreeOnGoingFragment.OnFinishListener() {
            @Override
            public void On_finish(int score, int max_score) {
                GameThreeOverDialog dialog = GameThreeOverDialog.newInstance(score,max_score);
                dialog.setOnConfirmListener(new GameThreeOverDialog.OnConfirmListener() {
                    @Override
                    public void OnConfirm() {
                        finish();
                    }
                });
                dialog.show(getSupportFragmentManager(),"game_three_over");
            }
        });
    }
}