package com.example.mytest.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.mytest.Common.CommonUtils;
import com.example.mytest.R;

public class LoadingDialog extends DialogFragment {
    private View view;
    private ImageView loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.loading_dialog,null);
        InitViews();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void InitViews() {
        loading = view.findViewById(R.id.loading_dialog_gif);
        Glide.with(getContext()).load(R.drawable.loading).into(loading);
    }

    public static LoadingDialog newInstance(){
        LoadingDialog dialog = new LoadingDialog();

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
        //window.setDimAmount(1.0f);
        window.setAttributes(layoutParams);
    }

    private void resizeDialogFragment() {
        Dialog dialog = getDialog();
        if(dialog != null){
            Window window = dialog.getWindow();
            WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
            if(window != null){
                window.setLayout(CommonUtils.dip2px(getContext(),170), CommonUtils.dip2px(getContext(),170));
            }
        }
    }
}
