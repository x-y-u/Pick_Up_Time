package com.example.mytest.ItemFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytest.R;

public class GameThreeStartFragment extends Fragment {
    private View view;
    private TextView textView;
    private OnStartListener onStartListener;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.game_three_start,container,false);
        textView = view.findViewById(R.id.game_three_start_bt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onStartListener!=null){
                    onStartListener.on_start();
                }
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        textView.setTextSize(23);
                        break;
                    case MotionEvent.ACTION_UP:
                        textView.setTextSize(27);
                        //finish();
                        break;
                }
                return false;
            }
        });
        return view;
    }

    public void setOnStartListener(OnStartListener onStartListener){
        this.onStartListener = onStartListener;
    }

    public interface OnStartListener{
        public void on_start();
    }
}
