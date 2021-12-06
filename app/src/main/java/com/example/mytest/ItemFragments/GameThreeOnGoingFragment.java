package com.example.mytest.ItemFragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class GameThreeOnGoingFragment extends Fragment implements View.OnClickListener , View.OnTouchListener {
    private List<String> titles,options_a,options_b,options_c,options_d;
    private TextView title,last,next;
    private ImageView imageView;
//    private View[] marks_bg = new View[10];
//    private TextView[] marks_tv = new TextView[10];
    private TextView[] options = new TextView[4];
    private Boolean[] is_answered = new Boolean[5];
    private int[] real_answers = new int[5];
    private int[] answers = new int[5];
    private List<Integer> images;
    private ImageView[] chooses = new ImageView[4];
    private View view;
    private int curindex,i,score,max_score;
    private OnFinishListener onFinishListener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.game_three_ongoing,container,false);
        InitViews();
        InitDatas();
        InitEvents();
        select(0);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitEvents() {
        for (i=0;i<4;i++){
            options[i].setOnClickListener(this);
        }
        last.setOnClickListener(this);
        next.setOnClickListener(this);
        last.setOnTouchListener(this);
        next.setOnTouchListener(this);
    }

    private void InitDatas() {
        curindex = 1;
        titles = new ArrayList<>();
        options_a = new ArrayList<>();
        options_b = new ArrayList<>();
        options_c = new ArrayList<>();
        options_d = new ArrayList<>();
        images = new ArrayList<>();
        for (i=0;i<5;i++){
            is_answered[i]=false;
        }
        real_answers[0] = 0;
        real_answers[1] = 2;
        real_answers[2] = 2;
        real_answers[3] = 3;
        real_answers[4] = 0;
        titles.add("这是浙江历史上的哪一次著名会议？");
        images.add(R.drawable.problem1);
        options_a.add("A. 中共一大");
        options_b.add("B．中共二大");
        options_c.add("C．中共三大");
        options_d.add("D．中共四大");
        titles.add("这是浙江省哪一地点？");
        images.add(R.drawable.problem2);
        options_a.add("A．目莲坞村");
        options_b.add("B．鲁家村");
        options_c.add("C．安吉余村");
        options_d.add("D．大竹园村");
        titles.add("这与哪位人物有关？");
        images.add(R.drawable.problem3);
        options_a.add("A. 粟裕");
        options_b.add("B. 金贯真");
        options_c.add("C. 陈望道");
        options_d.add("D. 裘古怀");
        titles.add("这与哪位历史人物有关？");
        images.add(R.drawable.problem4);
        options_a.add("A. 陈望道");
        options_b.add("B. 金贯真");
        options_c.add("C. 步鑫生");
        options_d.add("D. 裘古怀");
        titles.add("这与哪位历史人物有关？");
        images.add(R.drawable.problem5);
        options_a.add("A. 金贯真");
        options_b.add("B. 朱枫");
        options_c.add("C. 李大钊");
        options_d.add("D. 陈独秀");
    }

    private void InitViews() {
        imageView = view.findViewById(R.id.game_three_ongoing_img);
        options[0] = view.findViewById(R.id.game_three_ongoing_option_a);
        options[1] = view.findViewById(R.id.game_three_ongoing_option_b);
        options[2] = view.findViewById(R.id.game_three_ongoing_option_c);
        options[3] = view.findViewById(R.id.game_three_ongoing_option_d);
        chooses[0] = view.findViewById(R.id.game_three_ongoing_choose_a);
        chooses[1] = view.findViewById(R.id.game_three_ongoing_choose_b);
        chooses[2] = view.findViewById(R.id.game_three_ongoing_choose_c);
        chooses[3] = view.findViewById(R.id.game_three_ongoing_choose_d);
        title = view.findViewById(R.id.game_three_ongoing_title);
        last = view.findViewById(R.id.game_three_ongoing_last);
        next = view.findViewById(R.id.game_three_ongoing_next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game_three_ongoing_option_a:
                set_choose(0);
                break;
            case R.id.game_three_ongoing_option_b:
                set_choose(1);
                break;
            case R.id.game_three_ongoing_option_c:
                set_choose(2);
                break;
            case R.id.game_three_ongoing_option_d:
                set_choose(3);
                break;
            case R.id.game_three_ongoing_last:
                if (curindex>0){
                    select(curindex-1);
                }
                break;
            case R.id.game_three_ongoing_next:
                if (curindex<4){
                    select(curindex+1);
                }else {
                    if (is_all_answered()){
                        if (onFinishListener!=null){
                            calculate();
                            onFinishListener.On_finish(score,max_score);
                        }
                    }else {
                        Toast.makeText(getContext(),"还有题没做完噢~",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ((TextView)v).setTextSize(13);
                break;
            case MotionEvent.ACTION_UP:
                ((TextView)v).setTextSize(16);
                //finish();
                break;
        }
        return false;
    }

    private void select(int index){
        if (curindex!=index){
            imageView.setImageResource(images.get(index));
            title.setText(titles.get(index));
            options[0].setText(options_a.get(index));
            options[1].setText(options_b.get(index));
            options[2].setText(options_c.get(index));
            options[3].setText(options_d.get(index));
            if (index == 0){
                last.setVisibility(View.INVISIBLE);
            }else {
                last.setVisibility(View.VISIBLE);
            }
            if (index == 4){
                next.setText("提交");
            }else {
                next.setText("下一题");
            }
            curindex = index;
            if (is_answered[index]){
                set_choose(answers[index]);
            }else {
                reset_choose();
            }
        }
    }

    private Boolean is_all_answered(){
        for (i=0;i<5;i++){
            if (!is_answered[i]){
                //select(i);
                return false;
            }
        }
        return true;
    }

    public interface OnFinishListener{
        public void On_finish(int score,int max_score);
    }

    public void setOnFinishListener(OnFinishListener onFinishListener){
        this.onFinishListener = onFinishListener;
    }

    private void reset_choose(){
        for (i=0;i<4;i++){
            chooses[i].setVisibility(View.INVISIBLE);
        }
    }

    private void set_choose(int answer){
        if (!is_answered[curindex]){
            is_answered[curindex] = true;
            answers[curindex] = answer;
        }else {
            answers[curindex] = answer;
        }
        reset_choose();
        chooses[answer].setVisibility(View.VISIBLE);
    }

    private void calculate(){
        score = 0;
        max_score = 0;
        for (i=0;i<5;i++){
            if (answers[i] == real_answers[i]){
                score+=20;
            }
            max_score+=20;
        }
    }
}
