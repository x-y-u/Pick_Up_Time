package com.example.mytest.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.R;

public class GameThreeOverDialog extends DialogFragment {
    private View view;
    private TextView tv_score,tv_maxscore,tv_confirm;
    private int score,max_score;
    private OnConfirmListener onConfirmListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.game_three_over_dialog,null);
        InitViews();
        tv_score.setText(score+"");
        tv_maxscore.setText(max_score+"");
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitViews() {
        tv_score = view.findViewById(R.id.game_three_over_score);
        tv_maxscore = view.findViewById(R.id.game_three_over_maxscore);
        tv_confirm = view.findViewById(R.id.game_three_over_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmListener!=null){
                    dismiss();
                    onConfirmListener.OnConfirm();
                }
            }
        });
        tv_confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tv_confirm.setTextSize(15);
                        break;
                    case MotionEvent.ACTION_UP:
                        tv_confirm.setTextSize(18);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public static GameThreeOverDialog newInstance(int score,int max_score){
        GameThreeOverDialog dialog = new GameThreeOverDialog();
        dialog.score = score;
        dialog.max_score = max_score;

        // 设置主题，这里只能通过xml方式设置主题，不能通过Java代码处理，因为这是getWindow还是null，
        // 而且window的几乎所有属性，都可以通过xml设置
        dialog.setStyle(STYLE_NORMAL, R.style.MyDialogTheme);
        // 设置触摸、点击弹窗外部不可关闭
        dialog.setCancelable(true);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        resizeDialogFragment();
        resetDialogFragment();
    }

    private void resetDialogFragment() {
        getDialog().getWindow().setGravity(Gravity.CENTER);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //window.setDimAmount(0.0f);
        window.setAttributes(layoutParams);
    }

    private void resizeDialogFragment() {
        Dialog dialog = getDialog();
        if(dialog != null){
            Window window = dialog.getWindow();
            WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
            if(window != null){
                window.setLayout(CommonUtils.dip2px(getContext(),280), lp.WRAP_CONTENT);
            }
        }
    }

    public interface OnConfirmListener{
        public void OnConfirm();
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener){
        this.onConfirmListener = onConfirmListener;
    }
}
