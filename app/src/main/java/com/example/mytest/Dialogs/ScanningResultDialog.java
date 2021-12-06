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

public class ScanningResultDialog extends DialogFragment {
    private View view;
    private TextView tv_confirm,result;
    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.scanning_result_dialog,null);
        InitViews();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitViews() {
        tv_confirm = view.findViewById(R.id.scanning_result_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        result = view.findViewById(R.id.scanning_result_text);
        result.setText(address);
    }

    public static ScanningResultDialog newInstance(String address){
        ScanningResultDialog dialog = new ScanningResultDialog();
        dialog.address = address;
        // 设置主题，这里只能通过xml方式设置主题，不能通过Java代码处理，因为这是getWindow还是null，
        // 而且window的几乎所有属性，都可以通过xml设置
        dialog.setStyle(STYLE_NORMAL, R.style.MyDialogTheme);
        // 设置触摸、点击弹窗外部不可关闭
        dialog.setCancelable(false);
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
                window.setLayout(CommonUtils.dip2px(getContext(),240), CommonUtils.dip2px(getContext(),160));
            }
        }
    }
}
