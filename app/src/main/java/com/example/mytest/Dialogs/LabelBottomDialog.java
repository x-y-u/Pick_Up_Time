package com.example.mytest.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.Common.CommonUtils;
import com.example.mytest.MyAdapter.LabelsDialogRecyclerViewAdapter;
import com.example.mytest.R;

import java.util.List;

public class LabelBottomDialog extends DialogFragment {
    private View view;
    private List<String> labels;
    private RecyclerView recyclerView;
    private LabelsDialogRecyclerViewAdapter adapter;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public LabelBottomDialog(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.label_bottom_dialog,null);
        recyclerView = view.findViewById(R.id.label_bottom_dialog_rv);
        adapter = new LabelsDialogRecyclerViewAdapter(getContext(),labels);
        adapter.setOnItemClickListener(new LabelsDialogRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                if (listener!=null){
                    listener.OnItemClick(position);
                }
                dismiss();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public static LabelBottomDialog newInstance(List<String> labels) {
        LabelBottomDialog dialog = new LabelBottomDialog();
        dialog.labels = labels;
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
                window.setLayout(lp.width, CommonUtils.dip2px(getContext(),250));
            }
        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(int position);
    }
}
