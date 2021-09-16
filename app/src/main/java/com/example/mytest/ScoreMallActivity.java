package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mytest.Common.BaseStatusBarActivity;

public class ScoreMallActivity extends BaseStatusBarActivity {
    private ImageButton back;
    private ImageView[] ivs = new ImageView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_mall);
        back = findViewById(R.id.score_mall_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivs[0] = findViewById(R.id.score_mall1_1);
        ivs[1] = findViewById(R.id.score_mall1_2);
        ivs[2] = findViewById(R.id.score_mall1_3);
        for (int i=0;i<3;i++){
            ivs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ScoreMallActivity.this,"兑换成功",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}