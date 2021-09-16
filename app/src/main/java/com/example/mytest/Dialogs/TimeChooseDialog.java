package com.example.mytest.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.R;

import java.util.Calendar;

public class TimeChooseDialog extends DialogFragment {
    private View view;
    private DatePicker datePicker;
    private OnDateChangeListener onDateChangeListener;
    private TextView tv_cancel,tv_confirm;
    private int year,month,day;

    public void setOnDateChangeListener(OnDateChangeListener onDateChangeListener) {
        this.onDateChangeListener = onDateChangeListener;
    }

    public TimeChooseDialog(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.time_choose_dialog,null);

        InitViews();
        return view;
    }

    private void InitViews() {
        datePicker = view.findViewById(R.id.date_picker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TimeChooseDialog.this.year = year;
                TimeChooseDialog.this.month = monthOfYear;
                TimeChooseDialog.this.day = dayOfMonth;
            }
        });

        tv_cancel = view.findViewById(R.id.date_picker_cancel);
        tv_confirm = view.findViewById(R.id.date_picker_confirm);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDateChangeListener!=null){
                    onDateChangeListener.OnDateChange(year,month,day);
                }
                dismiss();
            }
        });
    }

    public static TimeChooseDialog newInstance(int year,int month,int day) {
        TimeChooseDialog dialog = new TimeChooseDialog();
        // 设置主题，这里只能通过xml方式设置主题，不能通过Java代码处理，因为这是getWindow还是null，
        // 而且window的几乎所有属性，都可以通过xml设置
        dialog.day = day;
        dialog.year = year;
        dialog.month = month;
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
                window.setLayout(lp.WRAP_CONTENT, lp.WRAP_CONTENT);
            }
        }
    }

    public interface OnDateChangeListener{
        public void OnDateChange(int year,int month,int day);
    }
}
