package com.example.mytest.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytest.Common.PaletteView;
import com.example.mytest.R;

public class GameTwoActivity extends AppCompatActivity implements View.OnClickListener {
    private PaletteView drawing_board;
    private ImageView back;
    private TextView last,next,pen,eraser,clear,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_two);
        InitViews();
        InitDatas();
        InitEvents();
    }

    private void InitEvents() {
        last.setOnClickListener(this);
        next.setOnClickListener(this);
        pen.setOnClickListener(this);
        eraser.setOnClickListener(this);
        save.setOnClickListener(this);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void InitDatas() {
    }

    private void InitViews() {
        drawing_board = findViewById(R.id.game2_drawing_board);
        last = findViewById(R.id.game2_last);
        next = findViewById(R.id.game2_next);
        pen = findViewById(R.id.game2_pen);
        eraser = findViewById(R.id.game2_eraser);
        save = findViewById(R.id.game2_save);
        clear = findViewById(R.id.game2_clear);
        back = findViewById(R.id.game2_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game2_last:
                drawing_board.undo();
                break;
            case R.id.game2_next:
                drawing_board.redo();
                break;
            case R.id.game2_pen:
                drawing_board.setMode(PaletteView.Mode.DRAW);
                break;
            case R.id.game2_eraser:
                drawing_board.setMode(PaletteView.Mode.ERASER);
                break;
            case R.id.game2_save:
                break;
            case R.id.game2_clear:
                drawing_board.clear();
                break;
            case R.id.game2_back:
                finish();
                break;
        }
    }
}