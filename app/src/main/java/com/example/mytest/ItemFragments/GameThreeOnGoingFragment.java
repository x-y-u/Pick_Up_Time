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
    private List<String> titles,options_a,options_b,options_c,options_d,helps,images;
    private TextView title,help,last,next;
    private ImageView imageView;
    private View[] marks_bg = new View[10];
    private TextView[] marks_tv = new TextView[10];
    private TextView[] options = new TextView[4];
    private Boolean[] is_answered = new Boolean[10];
    private int[] real_answers = new int[10];
    private int[] answers = new int[10];
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
        for (i=0;i<10;i++){
            marks_bg[i].setOnClickListener(this);
        }
    }

    private void InitDatas() {
        curindex = 1;
        titles = new ArrayList<>();
        options_a = new ArrayList<>();
        options_b = new ArrayList<>();
        options_c = new ArrayList<>();
        options_d = new ArrayList<>();
        helps = new ArrayList<>();
        images = new ArrayList<>();
        for (i=0;i<10;i++){
            titles.add("图中的人物是谁？"+i);
            options_a.add("陈望道"+i);
            options_b.add("陈望道"+i);
            options_c.add("陈望道"+i);
            options_d.add("陈望道"+i);
            helps.add("图中有线索"+i);
            is_answered[i]=false;
            real_answers[i] = 0;
        }
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
        help = view.findViewById(R.id.game_three_ongoing_help);
        last = view.findViewById(R.id.game_three_ongoing_last);
        next = view.findViewById(R.id.game_three_ongoing_next);
        marks_bg[0] = view.findViewById(R.id.progress1_bg);
        marks_bg[1] = view.findViewById(R.id.progress2_bg);
        marks_bg[2] = view.findViewById(R.id.progress3_bg);
        marks_bg[3] = view.findViewById(R.id.progress4_bg);
        marks_bg[4] = view.findViewById(R.id.progress5_bg);
        marks_bg[5] = view.findViewById(R.id.progress6_bg);
        marks_bg[6] = view.findViewById(R.id.progress7_bg);
        marks_bg[7] = view.findViewById(R.id.progress8_bg);
        marks_bg[8] = view.findViewById(R.id.progress9_bg);
        marks_bg[9] = view.findViewById(R.id.progress10_bg);
        marks_tv[0] = view.findViewById(R.id.progress1_tv);
        marks_tv[1] = view.findViewById(R.id.progress2_tv);
        marks_tv[2] = view.findViewById(R.id.progress3_tv);
        marks_tv[3] = view.findViewById(R.id.progress4_tv);
        marks_tv[4] = view.findViewById(R.id.progress5_tv);
        marks_tv[5] = view.findViewById(R.id.progress6_tv);
        marks_tv[6] = view.findViewById(R.id.progress7_tv);
        marks_tv[7] = view.findViewById(R.id.progress8_tv);
        marks_tv[8] = view.findViewById(R.id.progress9_tv);
        marks_tv[9] = view.findViewById(R.id.progress10_tv);
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
                if (curindex<9){
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
            case R.id.progress1_bg:
                select(0);
                break;
            case R.id.progress2_bg:
                select(1);
                break;
            case R.id.progress3_bg:
                select(2);
                break;
            case R.id.progress4_bg:
                select(3);
                break;
            case R.id.progress5_bg:
                select(4);
                break;
            case R.id.progress6_bg:
                select(5);
                break;
            case R.id.progress7_bg:
                select(6);
                break;
            case R.id.progress8_bg:
                select(7);
                break;
            case R.id.progress9_bg:
                select(8);
                break;
            case R.id.progress10_bg:
                select(9);
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
            help.setText(helps.get(index));
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
            if (index == 9){
                next.setText("提交");
            }else {
                next.setText("下一题");
            }
            if (is_answered[index]){
                set_choose(answers[index]);
            }else {
                reset_choose();
            }
            marks_bg[index].setBackground(getResources().getDrawable(R.drawable.circle_brown_selected_bg));
            marks_bg[curindex].setBackground(getResources().getDrawable(R.drawable.circle_brown_unselected_bg));
            marks_tv[index].setTextColor(Color.WHITE);
            marks_tv[curindex].setTextColor(Color.BLACK);
            curindex = index;
        }
    }

    private Boolean is_all_answered(){
        for (i=0;i<10;i++){
            if (!is_answered[i]){
                select(i);
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
        for (i=0;i<10;i++){
            if (answers[i] == real_answers[i]){
                score+=10;
            }
            max_score+=10;
        }
    }
}
