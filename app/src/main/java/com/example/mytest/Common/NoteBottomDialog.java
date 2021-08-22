package com.example.mytest.Common;

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

import androidx.fragment.app.DialogFragment;

import com.example.mytest.R;

public class NoteBottomDialog extends DialogFragment {
    private View view;

    public NoteBottomDialog(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.note_bottom_dialog,null);
        return view;
    }

    public static NoteBottomDialog newInstance() {
        NoteBottomDialog dialog = new NoteBottomDialog();
        // 设置主题，这里只能通过xml方式设置主题，不能通过Java代码处理，因为这是getWindow还是null，
        // 而且window的几乎所有属性，都可以通过xml设置
        dialog.setStyle(STYLE_NORMAL, R.style.MyDialogTheme);
        // 设置触摸、点击弹窗外部不可关闭
        dialog.setCancelable(true);
//        // 对于DialogFragment，设置外部传的参数，通过bundle设置，然后在onCreateView读取
//        Bundle bundle = new Bundle();
//        bundle.putLong("id", id);
//        bundle.putString("name", name);
//​
//        // 把外部传进的参数放到bundle里， 在onCreateView里通过继续getArguments()读取参数，
//        // 通过bundle来处理，是因为就算DialogFragment被重建了，也能恢复回来并初始化
//        dialog.setArguments(bundle);
        return dialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        resizeDialogFragment();
        resetDialogFragment();
    }

    private void resetDialogFragment() {
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
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
            lp.width = CommonUtils.getWindowWidth(getContext());
            if(window != null){
                window.setLayout(lp.width, lp.WRAP_CONTENT);
            }
        }
    }
}
